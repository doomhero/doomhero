package com.shangchain.contract.sdk;

public class ClientBuilder {
	private String address;
	
	private String privateKey;
	
	private String contractAddress;
	
	private String chainAddress;
	
	public ClientBuilder setAddress(String address) {
		this.address = address;
		return this;
	}
	
	public ClientBuilder setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
		return this;
	}
	
	public ClientBuilder setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
		return this;
	}
	
	public ClientBuilder setChainAddress(String chainAddress) {
		this.chainAddress = chainAddress;
		return this;
	}
	
	/**
	 * 创建 NFT 智能合约的调用客户端
	 * @return
	 * @throws ShangchainSdkException
	 */
	public ERC721Client buildERC721() throws ShangchainSdkException {
		check();
		return new ERC721Client(address, contractAddress, privateKey, chainAddress);
	}
	
	/**
	 * 创建交易市场的调用客户端
	 * @return
	 * @throws ShangchainSdkException
	 */
	public MarketplaceClient buildMarketplace() throws ShangchainSdkException {
		check();
		return new MarketplaceClient(address, contractAddress, privateKey, chainAddress);
	}
	
	private void check() throws ShangchainSdkException  {
		if(null == address || address.length() == 0) {
			throw new ShangchainSdkException("build request client failed: address not set");
		}
		if(null == privateKey || privateKey.length() == 0) {
			throw new ShangchainSdkException("build request client failed: privateKey not set");
		}
		if(null == chainAddress || chainAddress.length() == 0) {
			throw new ShangchainSdkException("build request client failed: chainAddress not set");
		}
		if(null == contractAddress || contractAddress.length() == 0) {
			throw new ShangchainSdkException("build request client failed: address not set");
		}
	}
}
