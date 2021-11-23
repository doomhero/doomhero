# 合约说明
1. 合约 Owner
可以转移 放弃 Owner 权限
对应接口：
```
	transferOwnership(address newOwner) 转移合约所有者
	renounceOwnership() 放弃 Owner 权限，执行后合约没有所有者
	owner() 返回合约的所有者
```
2. 权限 Owner，Manager，GameManager
	2.1 Owner 具有最高权限，可以添加/删除 Manager 和 GameManager
	2.2 Manager 可以添加/删除 GameManager
	对应接口：
	```
	addManager(address manager) 添加管理员
	removeManager(address manager) 移除管理员
	isManager(address manager) 判断是否是管理员
	addGameManager(address manager) 添加游戏管理员
	removeGameManager(address manager) 删除游戏管理员
	isGameManager(address manager) 判断是否是游戏管理员
	```
3. 交易暂停功能
```
	pause() 需要 Manager 权限执行
	unpause() 需要 Owner 权限执行
```
4. MetaData 元信息接口（NFT信息保存在外部服务器上，将 NFT 信息的链接保存在链上）
```
	name(), symbol()
	setTokenURIAffixes(string prefix, string suffix) 设置 NFT 信息链接的前缀和后缀
	tokenURI(uint256 tokenId) 返回 NFT 的 uri
```
5. ERC721 接口
```
	balanceOf(address _owner) 返回 owner 拥有的 nft 数量
	ownerOf(uint256 _tokenId) 返回 tokenId 对应的 NFT 所有者
	transferFrom(address _from, address _to, uint256 _tokenId) NFT 代理或者所有者把 tokenId 的 NFT 从 from 转移到 to
	approve(address _approved, uint256 _tokenId) NFT 所有者将 NFT 代理给 approved
	setApprovalForAll(address _operator, bool _approved) 所有者将自己的 NFT 管理权限代理给 operator
	getApproved(uint256 _tokenId) 获取某个 token 的代理（token 的代理只能有一个）
	isApprovedForAll(address _owner, address _operator) 判断 owner 的 NFT 权限是否代理给 operator
	totalSupply() 返回总的 NFT 数量
	tokenByIndex(uint256 _index) 根据 token 的索引获得 tokenId
	tokenOfOwnerByIndex(address _owner, uint256 _index) 根据 token 在 owner 所有 token 中的索引获得 tokenId
	mint(address _to, uint256 _tokenId) 发布一个 token 个地址为 to 的用户（需要 GameManager 及以上的权限）
```
