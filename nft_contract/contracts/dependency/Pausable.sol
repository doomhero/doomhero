pragma solidity ^0.6.12;


import "./Manager.sol";


contract Pausable is Manager {

  bool public paused = false;

  modifier whenNotPaused() {
    require(!paused, "ERROR: is not paused!");
    _;
  }

  modifier whenPaused {
    require(paused, "ERROR: is paused!");
    _;
  }

  function pause() external onlyManager whenNotPaused {
    paused = true;
  }

  function unpause() public onlyOwner whenPaused {
    paused = false;
  }
}