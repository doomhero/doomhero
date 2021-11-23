pragma solidity ^0.6.12;

import "./Ownable.sol";

contract Manager is Ownable {
    using SafeMath for uint256;

    mapping (address => bool) private _managers;

    mapping (address => bool) private _gameManagers;

    event AddManager(address indexed newManager);

    event RemoveManager(address indexed manager);

    event AddGameManager(address indexed newManager);

    event RemoveGameManager(address indexed manager);

    modifier onlyManager() {
        require(_managers[_msgSender()] || owner() == _msgSender(), "Ownable: caller is not the owner");
        _;
    }

    modifier onlyGameManager() {
        require(_gameManagers[_msgSender()] || _managers[_msgSender()] || owner() == _msgSender(), "Ownable: caller is not the owner");
        _;
    }

    function addManager(address newManager) public onlyOwner {
        require(newManager != address(0), "Ownable: new owner is the zero address");
        emit AddManager(newManager);
        _managers[newManager] = true;
    }

    function removeManager(address manager) public onlyOwner {
        require(manager != address(0), "Ownable: new owner is the zero address");
        emit RemoveManager(manager);
        delete _managers[manager];
    }

    function isManager(address manager) external view returns (bool) {
        return _managers[manager];
    }

    function addGameManager(address newManager) public onlyManager {
        require(newManager != address(0), "Ownable: new owner is the zero address");
        emit AddGameManager(newManager);
        _gameManagers[newManager] = true;
    }

    function removeGameManager(address manager) public onlyManager {
        require(manager != address(0), "Ownable: new owner is the zero address");
        emit RemoveGameManager(manager);
        delete _gameManagers[manager];
    }

    function isGameManager(address manager) external view returns (bool) {
        return _gameManagers[manager];
    }
}