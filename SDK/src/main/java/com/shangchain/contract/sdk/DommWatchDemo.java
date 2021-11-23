package com.shangchain.contract.sdk;

public class DommWatchDemo {
	public static String testNet = "https://data-seed-prebsc-1-s1.binance.org:8545/";
	
	// 合约地址
	public static String tokenContractAddress = "0x679747a4118966e1941121c19425f74dd0639ea7";
	public static String nftContractAddress = "0x79269ccc01346422dfbe147d6b6bf4323a53c8e3";
	public static String marketContractAddress = "0xfcf1126b873005b24d6c2cebe395751c02c67a97";

	// 测试账户（仅限测试网测试使用，请不要用到主网上）
	public static String ownerAddress = "0xe346181ae6aeeb88ad5a51368f8d640de4b10d48";
	public static String ownerPriky = "0x55531f8b33341e8505675d0d8ac427fb9612d91a5740c823bf5971b8ab5c8eac";
	public static String sellerAddress = "0x82da9bf0465581c5d54c19f07fc35145a4c5b95f";
	public static String sellerPriky = "0xba64f3125757dfe5bbb6e7bc65f471b4826d00d6b1962ee5bf2376cbb2679011";
	public static String buyerAddress = "0x1b48e3a7f32ed7c88498cdf75e95e7a4b375b782";
	public static String buyerPriky = "0xf174ce722718ce77faa7ea0581adf2f5671b7333e19fcdca0fae13ff0e59430b";
	
	// Builder
	public static ClientBuilder ownerBuilder = new ClientBuilder().setAddress(ownerAddress).setPrivateKey(ownerPriky).setChainAddress(testNet);
	public static ClientBuilder sellerBuilder = new ClientBuilder().setAddress(sellerAddress).setPrivateKey(sellerPriky).setChainAddress(testNet);
	public static ClientBuilder buyerBuilder = new ClientBuilder().setAddress(buyerAddress).setPrivateKey(buyerPriky).setChainAddress(testNet);
	
	
	public static void main(String[] args) throws Exception {
		String contractId = "0x79269ccc01346422dfbe147d6b6bf4323a53c8e3";
		
		String address1 = "0xe346181ae6aeeb88ad5a51368f8d640de4b10d48";
		String privateKey1 = "0x55531f8b33341e8505675d0d8ac427fb9612d91a5740c823bf5971b8ab5c8eac";
		
		String address2 = "0x82da9bf0465581c5d54c19f07fc35145a4c5b95f";
		String privateKey2 = "0xba64f3125757dfe5bbb6e7bc65f471b4826d00d6b1962ee5bf2376cbb2679011";
		
		String address3 = "0x1b48e3a7f32ed7c88498cdf75e95e7a4b375b782";
		String privateKey3 = "0xf174ce722718ce77faa7ea0581adf2f5671b7333e19fcdca0fae13ff0e59430b";
		
		String testNet = "https://data-seed-prebsc-1-s1.binance.org:8545/";
		
		ERC721Client rc = new ClientBuilder()
				.setAddress(address3)
				.setPrivateKey(privateKey3)
				.setContractAddress(contractId)
				.setChainAddress(testNet).buildERC721();
		System.out.println(rc.getChainId());
		System.out.println("build end");
		long tokenId = 234567;
		
		System.out.println(rc.ownerOf(tokenId));
		System.out.println(rc.tokenURI(tokenId));
		System.out.println(rc.owner());
		
		String hash = rc.transferFrom(address3, address2, tokenId);
		System.out.println(hash);
		Thread.sleep(3000);
		System.out.println(rc.ownerOf(tokenId));
		System.out.println(rc.getTransactionInfo(hash));
	}
}
