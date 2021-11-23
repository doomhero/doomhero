pragma solidity ^0.6.12;


import "./IERC721TokenReceiver.sol";


contract ERC721TokenReceiver is IERC721TokenReceiver {
  function onERC721Received(address, uint256, bytes memory) override external returns (bytes4) {
    return bytes4(keccak256("onERC721Received(address,uint256,bytes)"));
  }
}
