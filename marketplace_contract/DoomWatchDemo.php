<?php
	require_once ('vendor/autoload.php');
	use Web3\Web3;
	use Web3\Contract;
	use Web3\Utils;
	use Web3p\EthereumTx\Transaction;
	use Web3\Providers\HttpProvider;
	use Web3\RequestManagers\HttpRequestManager;
	set_time_limit(300);

	$utils = new Utils;

	$tokenAbi = '[{ "inputs": [], "stateMutability": "nonpayable", "type": "constructor" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "newManager", "type": "address" }], "name": "AddManager", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "owner", "type": "address" }, { "indexed": true, "internalType": "address", "name": "spender", "type": "address" }, { "indexed": false, "internalType": "uint256", "name": "value", "type": "uint256" }], "name": "Approval", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "previousOwner", "type": "address" }, { "indexed": true, "internalType": "address", "name": "newOwner", "type": "address" }], "name": "OwnershipTransferred", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "manager", "type": "address" }], "name": "RemoveManager", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "from", "type": "address" }, { "indexed": true, "internalType": "address", "name": "to", "type": "address" }, { "indexed": false, "internalType": "uint256", "name": "value", "type": "uint256" }], "name": "Transfer", "type": "event" }, { "inputs": [], "name": "_decimals", "outputs": [{ "internalType": "uint8", "name": "", "type": "uint8" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "_name", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "_symbol", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "newManager", "type": "address" }], "name": "addManager", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "owner", "type": "address" }, { "internalType": "address", "name": "spender", "type": "address" }], "name": "allowance", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "spender", "type": "address" }, { "internalType": "uint256", "name": "amount", "type": "uint256" }], "name": "approve", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "account", "type": "address" }], "name": "balanceOf", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "uint256", "name": "amount", "type": "uint256" }], "name": "burn", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "decimals", "outputs": [{ "internalType": "uint8", "name": "", "type": "uint8" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "spender", "type": "address" }, { "internalType": "uint256", "name": "subtractedValue", "type": "uint256" }], "name": "decreaseAllowance", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "getOwner", "outputs": [{ "internalType": "address", "name": "", "type": "address" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "spender", "type": "address" }, { "internalType": "uint256", "name": "addedValue", "type": "uint256" }], "name": "increaseAllowance", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "manager", "type": "address" }], "name": "isManager", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "recipient", "type": "address" }, { "internalType": "uint256", "name": "amount", "type": "uint256" }, { "internalType": "uint256", "name": "lockMonthNum", "type": "uint256" }], "name": "lockTo", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "uint256", "name": "amount", "type": "uint256" }], "name": "mint", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "name", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "owner", "outputs": [{ "internalType": "address", "name": "", "type": "address" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "manager", "type": "address" }], "name": "removeManager", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "renounceOwnership", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "account", "type": "address" }], "name": "spendable", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "symbol", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "totalSupply", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "recipient", "type": "address" }, { "internalType": "uint256", "name": "amount", "type": "uint256" }], "name": "transfer", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "sender", "type": "address" }, { "internalType": "address", "name": "recipient", "type": "address" }, { "internalType": "uint256", "name": "amount", "type": "uint256" }], "name": "transferFrom", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "newOwner", "type": "address" }], "name": "transferOwnership", "outputs": [], "stateMutability": "nonpayable", "type": "function" }]';
	$nftAbi = '[{ "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "newManager", "type": "address" }], "name": "AddGameManager", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "newManager", "type": "address" }], "name": "AddManager", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "_owner", "type": "address" }, { "indexed": true, "internalType": "address", "name": "_approved", "type": "address" }, { "indexed": false, "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "Approval", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "_owner", "type": "address" }, { "indexed": true, "internalType": "address", "name": "_operator", "type": "address" }, { "indexed": false, "internalType": "bool", "name": "_approved", "type": "bool" }], "name": "ApprovalForAll", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "previousOwner", "type": "address" }, { "indexed": true, "internalType": "address", "name": "newOwner", "type": "address" }], "name": "OwnershipTransferred", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "manager", "type": "address" }], "name": "RemoveGameManager", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "manager", "type": "address" }], "name": "RemoveManager", "type": "event" }, { "anonymous": false, "inputs": [{ "indexed": true, "internalType": "address", "name": "_from", "type": "address" }, { "indexed": true, "internalType": "address", "name": "_to", "type": "address" }, { "indexed": false, "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "Transfer", "type": "event" }, { "inputs": [{ "internalType": "address", "name": "newManager", "type": "address" }], "name": "addGameManager", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "newManager", "type": "address" }], "name": "addManager", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_approved", "type": "address" }, { "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "approve", "outputs": [], "stateMutability": "payable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_owner", "type": "address" }], "name": "balanceOf", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "getApproved", "outputs": [{ "internalType": "address", "name": "", "type": "address" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_owner", "type": "address" }, { "internalType": "address", "name": "_operator", "type": "address" }], "name": "isApprovedForAll", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "manager", "type": "address" }], "name": "isGameManager", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "manager", "type": "address" }], "name": "isManager", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_to", "type": "address" }, { "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "mint", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "name", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "pure", "type": "function" }, { "inputs": [], "name": "owner", "outputs": [{ "internalType": "address", "name": "", "type": "address" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "ownerOf", "outputs": [{ "internalType": "address", "name": "", "type": "address" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "pause", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "paused", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "manager", "type": "address" }], "name": "removeGameManager", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "manager", "type": "address" }], "name": "removeManager", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "renounceOwnership", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_from", "type": "address" }, { "internalType": "address", "name": "_to", "type": "address" }, { "internalType": "uint256", "name": "_tokenId", "type": "uint256" }, { "internalType": "bytes", "name": "_data", "type": "bytes" }], "name": "safeTransferFrom", "outputs": [], "stateMutability": "payable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_operator", "type": "address" }, { "internalType": "bool", "name": "_approved", "type": "bool" }], "name": "setApprovalForAll", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "string", "name": "_prefix", "type": "string" }, { "internalType": "string", "name": "_suffix", "type": "string" }], "name": "setTokenURIAffixes", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [{ "internalType": "bytes4", "name": "interfaceID", "type": "bytes4" }], "name": "supportsInterface", "outputs": [{ "internalType": "bool", "name": "", "type": "bool" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "symbol", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "pure", "type": "function" }, { "inputs": [{ "internalType": "uint256", "name": "_index", "type": "uint256" }], "name": "tokenByIndex", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_owner", "type": "address" }, { "internalType": "uint256", "name": "_index", "type": "uint256" }], "name": "tokenOfOwnerByIndex", "outputs": [{ "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "tokenURI", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "tokenURIPrefix", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "tokenURISuffix", "outputs": [{ "internalType": "string", "name": "", "type": "string" }], "stateMutability": "view", "type": "function" }, { "inputs": [], "name": "totalSupply", "outputs": [{ "internalType": "uint256", "name": "", "type": "uint256" }], "stateMutability": "view", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "_from", "type": "address" }, { "internalType": "address", "name": "_to", "type": "address" }, { "internalType": "uint256", "name": "_tokenId", "type": "uint256" }], "name": "transferFrom", "outputs": [], "stateMutability": "payable", "type": "function" }, { "inputs": [{ "internalType": "address", "name": "newOwner", "type": "address" }], "name": "transferOwnership", "outputs": [], "stateMutability": "nonpayable", "type": "function" }, { "inputs": [], "name": "unpause", "outputs": [], "stateMutability": "nonpayable", "type": "function" }]';
	$marketAbi = '[{"inputs":[{"internalType":"uint256","name":"_marketCut","type":"uint256"}],"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"previousOwner","type":"address"},{"indexed":true,"internalType":"address","name":"newOwner","type":"address"}],"name":"OwnershipTransferred","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"_nftAddress","type":"address"},{"indexed":true,"internalType":"uint256","name":"_tokenId","type":"uint256"}],"name":"SellCancelled","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"_nftAddress","type":"address"},{"indexed":true,"internalType":"uint256","name":"_tokenId","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"_price","type":"uint256"},{"indexed":false,"internalType":"address","name":"_seller","type":"address"}],"name":"SellCreated","type":"event"},{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"_nftAddress","type":"address"},{"indexed":true,"internalType":"uint256","name":"_tokenId","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"_totalPrice","type":"uint256"},{"indexed":false,"internalType":"address","name":"_buyer","type":"address"}],"name":"SellSuccessful","type":"event"},{"stateMutability":"nonpayable","type":"fallback"},{"inputs":[{"internalType":"address","name":"_nftAddress","type":"address"},{"internalType":"uint256","name":"_tokenId","type":"uint256"}],"name":"buy","outputs":[],"stateMutability":"payable","type":"function"},{"inputs":[{"internalType":"address","name":"_nftAddress","type":"address"},{"internalType":"uint256","name":"_tokenId","type":"uint256"}],"name":"cancelAuctionWhenPaused","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"_nftAddress","type":"address"},{"internalType":"uint256","name":"_tokenId","type":"uint256"}],"name":"cancelSell","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"_nftAddress","type":"address"},{"internalType":"uint256","name":"_tokenId","type":"uint256"},{"internalType":"uint256","name":"_price","type":"uint256"}],"name":"createSell","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"getMarketCut","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"_nftAddress","type":"address"},{"internalType":"uint256","name":"_tokenId","type":"uint256"}],"name":"getPrice","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"address","name":"_nftAddress","type":"address"},{"internalType":"uint256","name":"_tokenId","type":"uint256"}],"name":"getSell","outputs":[{"internalType":"address","name":"seller","type":"address"},{"internalType":"uint256","name":"price","type":"uint256"},{"internalType":"uint256","name":"startedAt","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"marketCut","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"owner","outputs":[{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"pause","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"paused","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"reclaim","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"","type":"address"},{"internalType":"uint256","name":"","type":"uint256"}],"name":"sells","outputs":[{"internalType":"address","name":"seller","type":"address"},{"internalType":"uint128","name":"price","type":"uint128"},{"internalType":"uint256","name":"startedAt","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"_marketCut","type":"uint256"}],"name":"setMarketCut","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"newOwner","type":"address"}],"name":"transferOwnership","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"unpause","outputs":[],"stateMutability":"nonpayable","type":"function"}]';

	$tokenContract = new Contract('https://data-seed-prebsc-1-s1.binance.org:8545/', $tokenAbi);
	$nftContract = new Contract('https://data-seed-prebsc-1-s1.binance.org:8545/', $nftAbi);
	$marketContract = new Contract('https://data-seed-prebsc-1-s1.binance.org:8545/', $marketAbi);

	$eth = $tokenContract -> getEth();

	$tokenContractAddress = "0x679747a4118966e1941121c19425f74dd0639ea7";
	$nftContractAddress = "0x79269ccc01346422dfbe147d6b6bf4323a53c8e3";
	$marketContractAddress = "0xbf9181fdb82be5b6a11bd22e16a042c115a3e7fc";

	$ownerAddress = '0xe346181ae6aeeb88ad5a51368f8d640de4b10d48';
	$sellerAddress = '0x82da9bf0465581c5d54c19f07fc35145a4c5b95f';
	$buyerAddress = "0x1b48e3a7f32ed7c88498cdf75e95e7a4b375b782";
	$ownerPrik = '0x55531f8b33341e8505675d0d8ac427fb9612d91a5740c823bf5971b8ab5c8eac';
	$sellerPriky = '0xba64f3125757dfe5bbb6e7bc65f471b4826d00d6b1962ee5bf2376cbb2679011';
	$buyerPriky = "0xf174ce722718ce77faa7ea0581adf2f5671b7333e19fcdca0fae13ff0e59430b";

	$tokenId = 633868;
	$sellAmount = 3000000;
	$defaultValue = 0;

	echo ($utils -> toHex(97, true)) . '<br/>';

	echo 'balance of owner: '.(getBalance($eth, $ownerAddress)).'<br>';
	echo 'balance of seller: '.(getBalance($eth, $sellerAddress)).'<br>';
	echo 'balance of buyer: '.(getBalance($eth, $buyerAddress)).'<br>';
	echo 'balance of market: '.(getBalance($eth, $marketContractAddress)).'<br>';

	echo '>> step 1: mint a token: '.$tokenId.'<br>';
	$mintData = '0x'.$nftContract->at($nftContractAddress)->getData('mint', $sellerAddress, $tokenId);
	$tx = sendTransactionAndWaitSuccess($eth, $utils, $ownerAddress, $nftContractAddress, $ownerPrik, $mintData, $defaultValue);
	echo "tx hash: " . ($tx -> transactionHash) . '<br>';
	$owner = ownerOf($nftContract, $nftContractAddress, $tokenId);
	echo "owner of ".$tokenId.': '.$owner. '<br>';

	echo '>> step 2: seller 把 token 代理给 market<br>';
	$appoveData = '0x'.$nftContract->at($nftContractAddress)->getData('setApprovalForAll', $marketContractAddress, true);
	$tx = sendTransactionAndWaitSuccess($eth, $utils, $sellerAddress, $nftContractAddress, $sellerPriky, $appoveData, $defaultValue);
	echo "tx hash: " . ($tx -> transactionHash) . '<br>';
	$approved = isApprovedForAll($nftContract, $nftContractAddress, $sellerAddress, $marketContractAddress);
	if($approved){
		echo  'approved success<br>';
	}else{
		echo  'approved failed<br>';
	}

	echo '>> step3: seller 挂单销售<br>';
	$createData = '0x'.$marketContract->at($marketContractAddress)->getData('createSell', $nftContractAddress, $tokenId, $sellAmount);
	$tx = sendTransactionAndWaitSuccess($eth, $utils, $sellerAddress, $marketContractAddress, $sellerPriky, $createData, $defaultValue);
	echo "tx hash: " . ($tx -> transactionHash) . '<br>';
	$sell = getSell($marketContract, $marketContractAddress, $nftContractAddress, $tokenId);
	echo "sell info: <br>";
	var_dump($sell);
	echo "<br>";

	// 使用 BNB 不需要此步骤
	// echo '>> step4: buyer 将 price 数量的 token 代理给市场<br>';
	// $tokenAppoveData = '0x'.$tokenContract->at($tokenContractAddress)->getData('approve', $marketContractAddress, $sellAmount);
	// $tx = sendTransactionAndWaitSuccess($eth, $utils, $buyerAddress, $tokenContractAddress, $buyerPriky, $tokenAppoveData);
	// echo "tx hash: " . ($tx -> transactionHash) . '<br>';
	// $allownce = allowance($tokenContract, $tokenContractAddress, $buyerAddress, $marketContractAddress);
	// echo "allownece: ".$buyerAddress.'>'.$marketContractAddress.': '.$allownce.'<br>';

	echo '>> step4: buyer 购买<br>';
	$buyData = '0x'.$marketContract->at($marketContractAddress)->getData('buy', $nftContractAddress, $tokenId);
	// 设置 value 为支付的金额，多余的金额，市场将返回
	$tx = sendTransactionAndWaitSuccess($eth, $utils, $buyerAddress, $marketContractAddress, $buyerPriky, $buyData, $sellAmount);
	echo "tx hash: " . ($tx -> transactionHash) . '<br>';
	$owner = ownerOf($nftContract, $nftContractAddress, $tokenId);
	echo "owner of ".$tokenId.': '.$owner. '<br>';

	echo "各个账户的变化:<br>";
	echo 'balance of owner: '.(getBalance($eth, $ownerAddress)).'<br>';
	echo 'balance of seller: '.(getBalance($eth, $sellerAddress)).'<br>';
	echo 'balance of buyer: '.(getBalance($eth, $buyerAddress)).'<br>';
	echo 'balance of market: '.(getBalance($eth, $marketContractAddress)).'<br>';

	
	echo '>> step5: 提取到 owner<br>';
	$reclaimData = '0x'.$marketContract->at($marketContractAddress)->getData('reclaim');
	$tx = sendTransactionAndWaitSuccess($eth, $utils, $ownerAddress, $marketContractAddress, $ownerPrik, $reclaimData, $defaultValue);
	echo "tx hash: " . ($tx -> transactionHash) . '<br>';
	echo 'balance of owner: '.(getBalance($eth, $ownerAddress)).'<br>';
	echo 'balance of market: '.(getBalance($eth, $marketContractAddress)).'<br>';


	function allowance($contract, $contractAddress, $address, $spendAddress) {
		$allownce = 0;
		$contract->at($contractAddress)->call('allowance', $address, $spendAddress, function ($err, $result) use(&$allownce) {
			$allownce = $result[0];
		});
		return $allownce;
	}

	function getSell($contract, $contractAddress, $nftAddress, $tokenId) {
		$sell = 0;
		$contract->at($contractAddress)->call('getSell', $nftAddress, $tokenId, function ($err, $result) use(&$sell) {
			$sell = $result;
		});
		return $sell;
	}

	function balanceOf($contract, $contractAddress, $address) {
		$balance = 0;
		$contract->at($contractAddress)->call('balanceOf', $address, function ($err, $result) use(&$balance) {
			$balance = $result[0];
		});
		return $balance;
	}

	function getBalance($eth, $address){
		$balance = 0;
		$eth -> getBalance($address, function($err, $result) use (&$balance){
			if ($err !== null) {
				echo 'get nonce error:' . $err . '<br/>';
			}
			$balance = $result;
		});
		return $balance;
	}

	function ownerOf($contract, $contractAddress, $tokenId) {
		$owner = '';
		$contract->at($contractAddress)->call('ownerOf', $tokenId, function ($err, $result) use(&$owner) {
			$owner = $result[0];
		});
		return $owner;
	}

	function isApprovedForAll($contract, $contractAddress, $address, $spendAddress) {
		$approved = '';
		$contract->at($contractAddress)->call('isApprovedForAll', $address, $spendAddress, function ($err, $result) use(&$approved) {
			$approved = $result[0];
		});
		return $approved;
	}

	/**
	 * 调用交易，定等待交易完成（写到块中），返回交易数据
	 * @param $eth 
	 * @param $utils
	 * @param $from 发起调用的地址
	 * @param $contractAddress 合约地址
	 * @param $priK 发起调用地址的私钥
	 * @param $functionData 调用的函数数据
	 * @param $value 交易转移的 BNB
	 * @return 返回交易hash
	 */
	function sendTransactionAndWaitSuccess($eth, $utils, $from, $contractAddress, $priK, $functionData, $value){
		try {
			$txHash = sendTransaction($eth, $utils, $from, $contractAddress, $priK, $functionData, $value);
			$txReceipt = null;
			for ($i=0; $i <= 10; $i++) {
				$eth->getTransactionReceipt($txHash, function ($err, $txReceiptResult) use(&$txReceipt) {
					if($err) { 
						echo $err;
					} else {
						$txReceipt = $txReceiptResult;
					}
				});

				if ($txReceipt) {
					break;
				}
				sleep(1);
			}
			return $txReceipt;
		} catch (Throwable $e) {
			echo "Captured Throwable: " . $e->getMessage() . '<br>';
		}
		return ['transactionHash'=>'null'];
	}

	/**
	 * 调用交易，并返回交易 hash 所有状态变化的交易都可通过本函进行
	 * @param $eth 
	 * @param $utils
	 * @param $from 发起调用的地址
	 * @param $contractAddress 合约地址
	 * @param $priK 发起调用地址的私钥
	 * @param $functionData 调用的函数数据
	 * @param $value 交易转移的 BNB
	 * @return 返回交易hash
	 */
	function sendTransaction($eth, $utils, $from, $contractAddress, $priK, $functionData, $value){
		// 获取 nonce 和 price
		$nonce = 0;
		$gasPrice = 0;
		$eth -> getTransactionCount($from, function($err, $result) use (&$nonce){
			if ($err !== null) {
				echo 'get nonce error:' . $err . '<br/>';
			}
			$nonce = $result;
		});

		$eth -> gasPrice(function($err, $result) use (&$gasPrice){
			if ($err !== null) {
				echo 'get price error:' . $err . '<br/>';
			}
			$gasPrice = $result;
		});

		// 估算 gasLimit
		$transactionInfo = ['from' => $from,'to' => $contractAddress,'data' => $functionData, 'value' => $value];

		$gasLimit = 0;
		$eth->estimateGas($transactionInfo, function($err, $gas) use (&$gasLimit) {
			if ($err !== null) {
				echo $err->getMessage();
				return false;
			} else {
				$gasLimit = $gas;
				return $gasLimit;
			}
		});

		// 生成签名的交易数据
		$transactionInfo['nonce'] = $utils -> toHex($nonce->toString(), true);
		$transactionInfo['gasPrice'] = $utils -> toHex($gasPrice->toString(), true);
		$transactionInfo['gas'] = $utils -> toHex($gasLimit->toString(), true);
		$transactionInfo['gasLimit'] = $utils -> toHex($gasLimit->toString(), true);
		$transactionInfo['chainId'] = 97;
		$transactionInfo['value'] = $utils -> toHex(strval($value), true);
		$transaction =new Transaction($transactionInfo);
		$signedTransaction = '0x'.$transaction->sign($priK);

		$txHash = null;
		$eth -> sendRawTransaction($signedTransaction, function($err, $tx) use (&$txHash) {
			if ($err !== null) {
				echo 'sendRawTransaction error: ' . $err . '<br/>';
			}else{
				$txHash = $tx;
			}
		});

		return $txHash;
	}
?>