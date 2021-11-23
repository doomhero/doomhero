package com.shangchain.contract.sdk;

import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.tx.gas.DefaultGasProvider;

public class TokenClient extends ContractClient {
	private BEP20Token token = null;
	
	TokenClient(String address, String contractAddress, String privateKey, String chainUrl)
			throws ShangchainSdkException {
		super(address, contractAddress, privateKey, chainUrl);
		this.token = BEP20Token.load(contractAddress, web3j, credentials, new DefaultGasProvider());
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
					BEP20Token.FUNC_TRANSFEROWNERSHIP, 
	                Arrays.<Type>asList(new Address(160, address)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 放弃合约所有权，调用后合约将没有 Owner
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String renounceOwnership() throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_RENOUNCEOWNERSHIP, 
	                Arrays.<Type>asList(), 
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
			return token.owner().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取合约 Owner
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String getOwner() throws ShangchainSdkException {
		try {
			return token.getOwner().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }

	/**
	 * 总发行量
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long totalSupply() throws ShangchainSdkException {
		try {
			return token.totalSupply().send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 代币名称
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String name() throws ShangchainSdkException {
		try {
			return token.name().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 代币符号
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String symbol() throws ShangchainSdkException {
		try {
			return token.symbol().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取币的计量单位
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long decimals() throws ShangchainSdkException {
		try {
			return token.decimals().send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取某个地址拥有的 token 数量
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long balanceOf(String address) throws ShangchainSdkException {
        try {
			return token.balanceOf(address).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * owner 允许 spender 使用多少的 token
	 * @param owner
	 * @param spender
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long allowance(String owner, String spender) throws ShangchainSdkException {
        try {
			return token.allowance(owner, spender).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 把合约调用者的币转 amount 到 recipient
	 * @param recipient
	 * @param amount
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String transfer(String recipient, long amount) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_TRANSFER, 
	                Arrays.<Type>asList(new Address(recipient), new Uint256(amount)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 合约调用者把 amount 数量的币交给 spender 代理
	 * @param spender
	 * @param amount
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String approve(String spender, long amount) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_APPROVE, 
	                Arrays.<Type>asList(new Address(spender), new Uint256(amount)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 合约的调用者把自己的代理的 sender 的币转移 amount 数量到 recipient 上
	 * @param sender
	 * @param recipient
	 * @param amount
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String transferFrom(String sender, String recipient, long amount) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_TRANSFERFROM, 
	                Arrays.<Type>asList(new Address(sender), new Address(recipient), new Uint256(amount)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 合约调用者增加 addedValue 数量的币给 spender 代理
	 * @param spender
	 * @param addedValue
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String increaseAllowance(String spender, long addedValue) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_INCREASEALLOWANCE, 
	                Arrays.<Type>asList(new Address(spender), new Uint256(addedValue)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 合约调用者减少 subtractedValue 数量的币给 spender 代理
	 * @param spender
	 * @param subtractedValue
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String decreaseAllowance(String spender, long subtractedValue) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_DECREASEALLOWANCE, 
	                Arrays.<Type>asList(new Address(spender), new Uint256(subtractedValue)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 增发 amount 数量的币，只有 Owner 或者 Manager 有操作权限
	 * @param amount
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String mint(long amount) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_MINT, 
	                Arrays.<Type>asList(new Uint256(amount)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 销毁 amount 数量的币
	 * @param amount
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String burn(long amount) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_BURN, 
	                Arrays.<Type>asList(new Uint256(amount)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * account 地址当前可花费的币的数量
	 * @param account
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long spendable(String account) throws ShangchainSdkException {
        try {
			return token.spendable(account).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 合约的所有者把 amount 的币转移给 recipient，但是 recipient 需要在 lockMonthNum 个月后才能使用，
	 * 并且使用的数量在接下来的 lockMonthNum 个月中逐月发放
	 * @param recipient
	 * @param amount
	 * @param lockMonthNum
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String lockTo(String recipient, long amount, long lockMonthNum) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                BEP20Token.FUNC_LOCKTO, 
	                Arrays.<Type>asList(new Address(recipient), new Uint256(amount), new Uint256(lockMonthNum)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 添加管理员
	 * @param address
	 * @return
	 * @throws WalletException 
	 */
	@SuppressWarnings("rawtypes")
	public String addManager(String address) throws ShangchainSdkException {
		try {
			Function function = new Function(
					BEP20Token.FUNC_ADDMANAGER, 
	                Arrays.<Type>asList(new Address(160, address)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 添加管理员
	 * @param address
	 * @return
	 * @throws WalletException 
	 */
	@SuppressWarnings("rawtypes")
	public String removeManager(String address) throws ShangchainSdkException {
		try {
			Function function = new Function(
					BEP20Token.FUNC_REMOVEMANAGER,
	                Arrays.<Type>asList(new Address(160, address)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 判断是否是管理员
	 * @param address
	 * @return
	 * @throws ShangchainSdkException
	 */
	public Boolean isManager(String address) throws ShangchainSdkException {
		try {
			return token.isManager(address).send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
}
