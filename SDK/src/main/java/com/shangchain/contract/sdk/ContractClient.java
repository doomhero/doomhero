package com.shangchain.contract.sdk;

import static okhttp3.ConnectionSpec.CLEARTEXT;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import com.alibaba.fastjson.JSONObject;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;

public class ContractClient {
	private static final CipherSuite[] INFURA_CIPHER_SUITES =
            new CipherSuite[] {
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256,

                // Note that the following cipher suites are all on HTTP/2's bad cipher suites list.
                // We'll
                // continue to include them until better suites are commonly available. For example,
                // none
                // of the better cipher suites listed above shipped with Android 4.4 or Java 7.
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384,
                CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
                CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA,
                CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA,

                // Additional INFURA CipherSuites
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,
                CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256,
                CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256
            };
	
	private static final ConnectionSpec INFURA_CIPHER_SUITE_SPEC =
            new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .cipherSuites(INFURA_CIPHER_SUITES)
                    .build();
	
	private List<ConnectionSpec> CONNECTION_SPEC_LIST = Arrays.asList(INFURA_CIPHER_SUITE_SPEC, CLEARTEXT);
	
	protected String address;
	
	protected Credentials credentials;
	
	protected Web3j web3j;
	
	protected String contractAddress;
	
	protected BigInteger nonce = null;
	
	private BigInteger gasPrice = new BigInteger("100000");
	private BigInteger gasLimit = new BigInteger("10000");
	
	ContractClient(String address, String contractAddress, String privateKey, String chainUrl) throws ShangchainSdkException {
		this.address = address;
		this.contractAddress = contractAddress;
		
		credentials = Credentials.create(privateKey);
		String fromAddress = credentials.getAddress();
		if(!address.equals(fromAddress)) {
			throw new ShangchainSdkException("address 和 privateKey 不匹配!");
		}
		
		final OkHttpClient.Builder builder =
                new OkHttpClient.Builder()
                .connectionSpecs(CONNECTION_SPEC_LIST).readTimeout(10000, TimeUnit.SECONDS);
        
		web3j = Web3j.build(new HttpService(chainUrl, builder.build(), true));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					nonce = getNonce();
				} catch (ShangchainSdkException e) {
					nonce = null;
				}
			}
		}).start();
	}
	
	/**
	 * 获取交易信息，返回 json 格式
	 * @param hash
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String getTransactionInfo(String hash) throws ShangchainSdkException {
		EthTransaction tr;
		try {
			tr = this.web3j.ethGetTransactionByHash(hash).send();
			if(null != tr.getError()) {
				throw new ShangchainSdkException("get transaction info failed: " + tr.getError().getCode() + ", " + tr.getError().getMessage() + ", " + tr.getError().getData());
			}
			JSONObject jo = JSONObject.parseObject(tr.getRawResponse());
			return JSONObject.toJSONString(jo.getJSONObject("result"));
		} catch (IOException e) {
			throw new ShangchainSdkException("get transaction info failed", e);
		}
	}
	
	/**
	 * 发送一个交易调用函数，返回交易 hash
	 * @param function
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String sendTransaction(Function function) throws ShangchainSdkException {
        try {
        	if(nonce == null) {
        		nonce = this.getNonce();
        	}
        	BigInteger nonce = this.nonce.add(new BigInteger("1"));
        	this.nonce = nonce;
    		String functionStr = FunctionEncoder.encode(function);
	        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, contractAddress, functionStr);
	        
	        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
			
			String hexValue = Numeric.toHexString(signedMessage);
			if (null != hexValue && hexValue.length() > 0) {
                EthSendTransaction send = web3j.ethSendRawTransaction(hexValue).send();
                String txHash = send.getTransactionHash();
                return txHash;
            
			}
        } catch (IOException e) {
            throw new ShangchainSdkException("send transaction error", e);
        }
		return null;
	}
	
	/**
	 * 获取 Nonce
	 * @return
	 * @throws WalletException
	 */
	public BigInteger getNonce() throws ShangchainSdkException {
		EthGetTransactionCount ethGetTransactionCount;
		try {
			ethGetTransactionCount = web3j.ethGetTransactionCount(
			        address, DefaultBlockParameterName.LATEST).sendAsync().get();
			BigInteger nonce = ethGetTransactionCount.getTransactionCount();
			return  nonce;
		} catch (Exception e) {
			throw new ShangchainSdkException("get nonce failed", e);
		}
	}
	
	/**
	 * 获取链 ID
	 * @return
	 * @throws IOException
	 */
	public long getChainId() throws IOException {
		return this.web3j.ethChainId().send().getChainId().longValue();
	}
	
	/**
	 * 估算手续费上限
	 * @param web3j
	 * @param transaction
	 * @return
	 * @throws WalletException 
	 */
	public BigInteger estimateGasLimit(Transaction transaction) throws ShangchainSdkException {
        try {
            EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(transaction).send();
            if (ethEstimateGas.hasError()){
                throw new RuntimeException(ethEstimateGas.getError().getMessage());
            }
            return ethEstimateGas.getAmountUsed();
        } catch (Exception e) {
            throw new ShangchainSdkException("estimate gas limit failed", e);
        }
    }
	
	/**
	 * 获取当前的交易费用
	 * @return
	 * @throws Exception
	 */
	public BigInteger getCurrentGasPrice() throws ShangchainSdkException {
        try {
			return web3j.ethGasPrice().send().getGasPrice();
		} catch (IOException e) {
			throw new ShangchainSdkException("get current gas price failed", e);
		}
    }

	public BigInteger getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(BigInteger gasPrice) {
		this.gasPrice = gasPrice;
	}

	public BigInteger getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(BigInteger gasLimit) {
		this.gasLimit = gasLimit;
	}
}
