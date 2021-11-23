package com.shangchain.contract.sdk;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.tx.gas.DefaultGasProvider;


public class ERC721Client extends ContractClient {
	private DoomERC721 erc;
	
	ERC721Client(String address, String contractAddress, String privateKey, String chainUrl) throws ShangchainSdkException {
		super(address, contractAddress, privateKey, chainUrl);
		this.erc = DoomERC721.load(contractAddress, web3j, credentials, new DefaultGasProvider());
	}
	
	/**
	 * 获取某个地址拥有的 NFT 数量
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long balanceOf(String address) throws ShangchainSdkException {
        try {
			return erc.balanceOf(address).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 返回 tokenId 对应的 NFT 所有者
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String ownerOf(long tokenId) throws ShangchainSdkException {
        try {
			return erc.ownerOf(new BigInteger(tokenId + "")).send();
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
			return erc.totalSupply().send().longValue();
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
			return erc.name().send();
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
			return erc.symbol().send();
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
			return erc.owner().send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
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
	 * 放弃合约所有权，调用后合约将没有 Owner
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String renounceOwnership() throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_RENOUNCEOWNERSHIP, 
	                Arrays.<Type>asList(), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	
	
	/**
	 * 添加游戏管理员（需要 manager 权限）
	 * @param address
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String addGameManager(String address) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_ADDGAMEMANAGER, 
	                Arrays.<Type>asList(new Address(160, address)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 删除游戏管理员
	 * @param address
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String removeGameManager(String address) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_REMOVEGAMEMANAGER,
	                Arrays.<Type>asList(new Address(160, address)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 判断是否是游戏管理员
	 * @param address
	 * @return
	 * @throws ShangchainSdkException
	 */
	public Boolean isGameManager(String address) throws ShangchainSdkException {
		try {
			return erc.isGameManager(address).send();
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
	                DoomERC721.FUNC_ADDMANAGER, 
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
	                DoomERC721.FUNC_REMOVEMANAGER,
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
			return erc.isManager(address).send();
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
	                DoomERC721.FUNC_PAUSE, 
	                Arrays.<Type>asList(), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 重新开始币交易
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String unpause() throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_UNPAUSE, 
	                Arrays.<Type>asList(), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 设置 tokenURI 的前缀和后缀
	 * @param prefix
	 * @param suffix
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String setTokenURIAffixes(String prefix, String suffix) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_SETTOKENURIAFFIXES, 
	                Arrays.<Type>asList(new Utf8String(prefix), 
	                new Utf8String(suffix)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取 NFT 的 uri
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String tokenURI(long tokenId) throws ShangchainSdkException {
		try {
			return erc.tokenURI(new BigInteger(tokenId + "")).send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 根据索引获取 NFT 的 id
	 * @param index
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long tokenByIndex(long index) throws ShangchainSdkException {
		try {
			return erc.tokenByIndex(new BigInteger(index + "")).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 根据 token 在 owner 所有 token 中的索引获得 tokenId
	 * @param owner
	 * @param index
	 * @return
	 * @throws ShangchainSdkException
	 */
	public long tokenOfOwnerByIndex(String owner, long index) throws ShangchainSdkException {
		try {
			return erc.tokenOfOwnerByIndex(owner, new BigInteger(index + "")).send().longValue();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 所有者将自己的 NFT 管理权限代理给 operator（approved=true）
	 * @param operator
	 * @param approved
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String setApprovalForAll(String operator, boolean approved) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_SETAPPROVALFORALL, 
	                Arrays.<Type>asList(new Address(160, address), 
	                		new Bool(approved)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 判断 owner 的 NFT 权限是否代理给 operator
	 * @param owner
	 * @param operator
	 * @return
	 * @throws ShangchainSdkException
	 */
	public Boolean isApprovedForAll(String owner, String operator) throws ShangchainSdkException {
		try {
			return erc.isApprovedForAll(owner, operator).send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * NFT 所有者将 NFT 代理给 approved
	 * @param approved
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String approve(String approved, long tokenId) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_APPROVE, 
	                Arrays.<Type>asList(new Address(160, approved), 
	                		new Uint256(tokenId)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 获取某个 token 的代理（token 的代理只能有一个）
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	public String getApproved(long tokenId) throws ShangchainSdkException {
		try {
			return erc.getApproved(new BigInteger(tokenId + "")).send();
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * NFT 代理或者所有者把 tokenId 的 NFT 从 from 转移到 to
	 * @param from
	 * @param to
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String transferFrom(String from, String to, long tokenId) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_TRANSFERFROM, 
	                Arrays.<Type>asList(new Address(160, from), 
	                		new Address(160, to), 
	                		new Uint256(tokenId)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	 /* 
	  * NFT 代理或者所有者把 tokenId 的 NFT 从 from 转移到 to
	 * @param from
	 * @param to
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String safeTransferFrom(String from, String to, long tokenId) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_SAFETRANSFERFROM, 
	                Arrays.<Type>asList(new Address(160, from), 
	                		new Address(160, to), 
	                		new Uint256(tokenId)), 
	                Collections.<TypeReference<?>>emptyList());
			return sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
	
	/**
	 * 发布一个 token 个地址为 to 的用户（需要 GameManager 及以上的权限）
	 * @param to
	 * @param tokenId
	 * @return
	 * @throws ShangchainSdkException
	 */
	@SuppressWarnings("rawtypes")
	public String mint(String to, long tokenId) throws ShangchainSdkException {
		try {
			Function function = new Function(
	                DoomERC721.FUNC_MINT, 
	                Arrays.<Type>asList(new Address(160, to), 
	                		new Uint256(tokenId)), 
	                Collections.<TypeReference<?>>emptyList());
			return this.sendTransaction(function);
		} catch (Exception e) {
			throw new ShangchainSdkException("error: " + e.getMessage(), e);
		}
    }
}
