pragma solidity ^0.6.12;


import "./erc/IERC721Metadata.sol";
import "./DoomERC721BaseEnumerable.sol";


contract DoomERC721Metadata is DoomERC721BaseEnumerable, IERC721Metadata {
  string public tokenURIPrefix = "https://localhost:8080/erc/721/list/";
  string public tokenURISuffix = ".json";

  constructor() internal {
    supportedInterfaces[0x5b5e139f] = true; // ERC-721 Metadata
  }

  function name() override external pure returns (string memory) {
    return "Doom";
  }

  function symbol() override external pure returns (string memory) {
    return "DOOM";
  }

  function setTokenURIAffixes(string memory _prefix, string memory _suffix) external onlyOwner {
    tokenURIPrefix = _prefix;
    tokenURISuffix = _suffix;
  }

  function tokenURI(
    uint256 _tokenId
  ) override
    external
    view
    mustBeValidToken(_tokenId)
    returns (string memory)
  {
    bytes memory _tokenURIPrefixBytes = bytes(tokenURIPrefix);
    bytes memory _tokenURISuffixBytes = bytes(tokenURISuffix);
    uint256 _suffixLength = _tokenURISuffixBytes.length;
    uint256 _tmpTokenId = _tokenId;
    uint256 _length;

    do {
      _length++;
      _tmpTokenId /= 10;
    } while (_tmpTokenId > 0);

    bytes memory _tokenURIBytes = new bytes(_tokenURIPrefixBytes.length + _length + _suffixLength);
    uint256 _i = _tokenURIBytes.length - _suffixLength - 1;

    _tmpTokenId = _tokenId;

    do {
      _tokenURIBytes[_i--] = byte(uint8(48 + _tmpTokenId % 10));
      _tmpTokenId /= 10;
    } while (_tmpTokenId > 0);

    for (_i = 0; _i < _tokenURIPrefixBytes.length; _i++) {
      _tokenURIBytes[_i] = _tokenURIPrefixBytes[_i];
    }

    for (_i = 0; _i < _tokenURISuffixBytes.length; _i++) {
      _tokenURIBytes[_tokenURIBytes.length + _i - _suffixLength] = _tokenURISuffixBytes[_i];
    }

    return string(_tokenURIBytes);
  }
}