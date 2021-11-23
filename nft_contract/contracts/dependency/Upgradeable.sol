pragma solidity ^0.6.12;


import "./Pausable.sol";


contract Upgradeable is Pausable {

  address public newContractAddress;

  event ContractUpgraded(address _newAddress);

  function setNewAddress(address _newAddress) external onlyOwner whenPaused {
    newContractAddress = _newAddress;
    ContractUpgraded(_newAddress);
  }
}