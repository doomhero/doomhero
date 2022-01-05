# Contract Info
[正式网地址](https://bscscan.com/address/0xc103147c34d306CBDaA9785B46258A97885320f6)：	```0xc103147c34d306CBDaA9785B46258A97885320f6```

[测试网地址](https://testnet.bscscan.com/address/0x79269ccc01346422dfbe147d6b6bf4323a53c8e3)：	```0x79269ccc01346422dfbe147d6b6bf4323a53c8e3```


# Contract specification
1. Contract Owner  ( Can be transfer or renounce Owner  )
  Some Interface：

  **Transfer contract owner.**

  ```
  transferOwnership(address newOwner) 
  ```

  **renounce Contract Owner, Contract will not have owner after execute this functions.**

  ```
  renounceOwnership()
  ```

  **retuen contract owner.**

  ```
  owner()
  ```

2. Authority: Owner，Manager，GameManager
	2.1 Owner has the highest privileges who can add/delete *Manager* and *GameManager*
	2.2 Manager can add/delete *GameManager*
	Some Interface：
	
	**add Manager**
	
	```
	addManager(address manager)
	```
	
	**remove Manager**
	
	```
	removeManager(address manager)
	```
	
	**check whether you are a Manager**
	
	```
	isManager(address manager)
	```
	
	**add GameManager**
	
	```
	addGameManager(address manager)
	```
	
	**remove GameManager**
	
	```
	removeGameManager(address manager)
	```
	
	**check whether you are a GameManager**
	
	```
	isGameManager(address manager)
	```
	
3. Trading pause function

   **need Manager authority to execute**

   ```
   pause()
   ```

   **need Owner authority to execute**

   ```
   unpause()
   ```

4. MetaData (Meta information interface, NFT information is stored on an external server and links to NFT information are stored on a chain.)

   ```
   name(), symbol()
   ```

   **set nft uri prefix && suffix **

   ```
   setTokenURIAffixes(string prefix, string suffix)
   ```

   **return uri of nft**

   ```
   tokenURI(uint256 tokenId)
   ```

5. ERC721 Interface

   **return number of owner's nft**

   ```
   balanceOf(address _owner)
   ```

   **return owner of token with @parm _tokenId**

   ```
   ownerOf(uint256 _tokenId)
   ```

   **transfer token from '*from*' to '*to*', need nft owner or approved address.**

   ```
   transferFrom(address _from, address _to, uint256 _tokenId)
   ```

   **approved nft to address @parm _approved**

   ```
   approve(address _approved, uint256 _tokenId)
   ```

   **The owner delegates his NFT administrative rights to the operator**

   ```
   setApprovalForAll(address _operator, bool _approved)
   ```

   **get nft approved**

   ```
   getApproved(uint256 _tokenId)
   ```

   **return true if nft was approved to operator**

   ```
   isApprovedForAll(address _owner, address _operator)
   ```

   **return totalSupply**

   ```
   totalSupply()
   ```

   **return tokenId by _index**

   ```
   tokenByIndex(uint256 _index)
   ```

   **return tokenId on owner's token list by _index**

   ```
   tokenOfOwnerByIndex(address _owner, uint256 _index)
   ```

   **mint nft(need authority above *GameManager*)**

   ```
   mint(address _to, uint256 _tokenId)
   ```

   
