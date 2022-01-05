# 合约地址

## DHD

[正式网合约地址](https://bscscan.com/address/0xac55b04af8e9067d6c53785b34113e99e10c2a11)：```0xac55b04af8e9067d6c53785b34113e99e10c2a11```

[测试网合约地址](https://testnet.bscscan.com/address/0x679747a4118966e1941121c19425f74dd0639ea7)：```0x679747a4118966e1941121c19425f74DD0639eA7```


## [DHG]

[合约地址](https://bscscan.com/address/0xbba24300490443bb0e344bf6ec11bac3aa91df72)：```0xbba24300490443bb0e344bf6ec11bac3aa91df72```
[测试网地址](https://testnet.bscscan.com/address/0x97694e813c2e862e024a98523dcac479739dafe9)：```0x97694e813c2e862e024a98523dcac479739dafe9```





# 相关函数

1. totalSupply() 
获取币的总发行量

2. decimals() 
获取币的计量单位

3. symbol() 
返回币的代号，如 ETH、BNB 

4. name() 
返回币的名称 
5. getOwner() 
返回合约的所有者

6. balanceOf(address account)  
获取地址 account 持有的币的数量

7. transfer(address recipient, uint256 amount) 
把合约调用者的币转 amount 到 recipient

8. allowance(address _owner, address spender) 
获取地址为 owner 的用户允许 spender 代理的币的数量

9. approve(address spender, uint256 amount) 
合约调用者把 amount 数量的币交给 spender 代理

10. transferFrom(address sender, address recipient, uint256 amount) 
合约的调用者把自己的代理的 sender 的币转移 amount 数量到 recipient 上

11. increaseAllowance(address spender, uint256 addedValue) 
合约调用者增加 addedValue 数量的币给 spender 代理

12. decreaseAllowance(address spender, uint256 subtractedValue) 
合约调用者减少 subtractedValue 数量的币给 spender 代理

13. mint(uint256 amount) 
增发 amount 数量的币 （需要 manager 权限）

14. burn(uint256 amount) 
合约调用者，烧掉自己持有的 amount 数量的币

15. spendable(address account) 
account 地址当前可花费的币的数量

16. lockTo(address recipient, uint256 amount, uint256 lockMonthNum) 
合约的所有者把 amount 的币转移给 recipient，但是 recipient 需要在 lockMonthNum 个月后才能使用，并且使用的数量在接下来的 lockMonthNum 个月中逐月发放

17. addManager(address newManager) 
增加一个管理员 （需要 owner 权限） 

18. removeManager(address manager) 
删除一个管理员 （需要 owner 权限） 

19. isManager(address manager) 
判断某个地址是否是管理员

20. owner() 
返回合约所有者，与 getOwner 相同

21. renounceOwnership() 
放弃合约的所有权，使合约没有所有者 （需要 owner 权限） 

22. transferOwnership(address newOwner) 
把合约的所有权转移给 newOwner （需要 owner 权限）

