pragma solidity 0.6.12;

import "./Ownable.sol";

contract Manager is Ownable {
    using SafeMath for uint256;

    mapping (address => uint256) private _managers;

    event AddManager(address indexed newManager);

    event RemoveManager(address indexed manager);

    modifier onlyManager() {
        require(_managers[_msgSender()] == 1 || owner() == _msgSender(), "Ownable: caller is not the owner");
        _;
    }

    function addManager(address newManager) public onlyOwner {
        require(newManager != address(0), "Ownable: new owner is the zero address");
        emit AddManager(newManager);
        _managers[newManager] = 1;
    }

    function removeManager(address manager) public onlyOwner {
        require(manager != address(0), "Ownable: new owner is the zero address");
        emit RemoveManager(manager);
        delete _managers[manager];
    }

    function isManager(address manager) external view returns (bool) {
        return _managers[manager] == 1;
    }
}