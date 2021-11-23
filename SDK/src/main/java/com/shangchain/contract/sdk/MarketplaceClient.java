package com.shangchain.contract.sdk;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.DefaultGasProvider;

import com.alibaba.fastjson.JSONObject;

public class MarketplaceClient extends ContractClient {
	
	private Marketplace market = null;
	
	MarketplaceClient(String address, String contractAddress, String privateKey, String chainUrl)
			throws ShangchainSdkException {
		super(address, contractAddress, privateKey, chainUrl);
		this.market = Marketplace.load(contractAddress, web3j, credentials, new DefaultGasProvider());
	}

	/**
	 * 转移所有权
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String transferOwnership() throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_TRANSFEROWNERSHIP, 
	                Arrays.<Type>asList(new Address(160, address)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取合约 Owner
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String owner() throws ShangchainSdkException {
		try {
			return market.owner().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 暂停交易
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String pause() throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_PAUSE, 
	                Arrays.<Type>asList(), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String unpause() throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_UNPAUSE, 
	                Arrays.<Type>asList(), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 提取 Token
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String reclaimToken() throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_RECLAIMTOKEN, 
	                Arrays.<Type>asList(), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取市场抽水比例
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long getMarketCut() throws ShangchainSdkException {
		try {
			return market.getMarketCut().send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	
	/**
	 * 获取市场交易使用的 token 合约地址
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String getTokenAddress() throws ShangchainSdkException {
		try {
			return market.getTokenAddress().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	
	/**
	 * 获取某个 token 的销售信息
	 * @param nftAddress
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String getSell(String nftAddress, long tokenId) throws ShangchainSdkException {
		try {
			Tuple3<String, BigInteger, BigInteger> ret =  market.getSell(nftAddress, new BigInteger(tokenId + "")).send();
			JSONObject jo = new JSONObject();
			jo.put("seller", ret.component1());
			jo.put("price", ret.component2().longValue());
			jo.put("startAt", ret.component3().longValue());
			return jo.toJSONString();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取 token 的售卖价格
	 * @param nftAddress
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long getPrice(String nftAddress, long tokenId) throws ShangchainSdkException {
		try {
			return market.getPrice(nftAddress, new BigInteger(tokenId + "")).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 设置市场抽水比例
	 * @param marketCut
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String setMarketCut(long marketCut) throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_SETMARKETCUT, 
	                Arrays.<Type>asList(new Uint256(marketCut)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 将 tokenId 的 NFT 挂单销售
	 * @param nftAddress
	 * @param tokenId
	 * @param price
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String createSell(String nftAddress, long tokenId, long price) throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_CREATESELL, 
	                Arrays.<Type>asList(new Address(nftAddress), new Uint256(tokenId), new Uint256(price)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 购买
	 * @param nftAddress
	 * @param tokenId
	 * @param buyAmount
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String buy(String nftAddress, long tokenId, long buyAmount) throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_BUY, 
	                Arrays.<Type>asList(new Address(nftAddress), new Uint256(tokenId), new Uint256(buyAmount)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 取消挂单销售
	 * @param nftAddress
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String  cancelSell(String nftAddress, long tokenId) throws ShangchainSdkException {
		try {
			Function function = new Function(
					Marketplace.FUNC_CANCELSELL, 
	                Arrays.<Type>asList(new Address(nftAddress), new Uint256(tokenId)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
}
