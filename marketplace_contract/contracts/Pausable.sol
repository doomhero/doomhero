pragma solidity ^0.6.12;


import "./Ownable.sol";


contract Pausable is Ownable {

  bool public paused = false;

  modifier whenNotPaused() {
    require(!paused, "ERROR: is not paused!");
    _;
  }

  modifier whenPaused {
    require(paused, "ERROR: is paused!");
    _;
  }

  function pause() external onlyOwner whenNotPaused {
    paused = true;
  }

  function unpause() public onlyOwner whenPaused {
    paused = false;
  }
}