pragma solidity ^0.6.12;

import "./Pausable.sol";
import "./IERC721Base.sol";
import "./IBEP20.sol";
import "./SafeBEP20.sol";

contract Marketplace is Pausable{
    using SafeBEP20 for IBEP20;

    struct Sell {
        // Current owner of NFT
        address seller;
        // Price (in wei)
        uint128 price;
        // Time when sell started
        // NOTE: 0 if this auction has been concluded
        uint256 startedAt;
    }

    // Values 0-10,000 map to 0%-100%
    uint256 public marketCut;

    // Map from token ID to their corresponding sell.
    mapping (address => mapping (uint256 => Sell)) public sells;

    event SellCreated(
        address indexed _nftAddress,
        uint256 indexed _tokenId,
        uint256 _price,
        address _seller
    );

    event SellSuccessful(
        address indexed _nftAddress,
        uint256 indexed _tokenId,
        uint256 _totalPrice,
        address _buyer
    );

    event SellCancelled(
        address indexed _nftAddress,
        uint256 indexed _tokenId
    );

    constructor(uint256 _marketCut) public {
        require(_marketCut <= 10000, "ERROR: market cut must less than 10000");
        marketCut = _marketCut;
    }

    // @dev DON'T give me your money.
    fallback() external {}

    // Modifiers to check that inputs can be safely stored with a certain
    // number of bits. We use constants and multiple modifiers to save gas.
    modifier canBeStoredWith64Bits(uint256 _value) {
        require(_value <= 18446744073709551615, "ERROR: value invalid");
        _;
    }

    modifier canBeStoredWith128Bits(uint256 _value) {
        require(_value < 340282366920938463463374607431768211455, "ERROR: value invalid");
        _;
    }

    /**
   * @dev Transfer all BNB held by the contract to the owner.
   */
    function reclaim() external onlyOwner {
        address payable owner = payable(owner());   
        owner.transfer(address(this).balance);
    }

    function setMarketCut(uint256 _marketCut) external onlyOwner {
        require(_marketCut <= 10000, "ERROR: market cut must less than 10000");
        marketCut = _marketCut;
    }

    function getMarketCut() external view returns (uint256) {
        return marketCut;
    }

    function getSell(
        address _nftAddress,
        uint256 _tokenId
    )
        external
        view
        returns (
            address seller,
            uint256 price,
            uint256 startedAt
        )
    {
        Sell storage _sell = sells[_nftAddress][_tokenId];
        require(_isOnSell(_sell), "ERROR: not on sell");
        return (
            _sell.seller,
            _sell.price,
            _sell.startedAt
        );
    }

    function getPrice(
        address _nftAddress,
        uint256 _tokenId
    )
        external
        view
        returns (uint256)
    {
        Sell storage _sell = sells[_nftAddress][_tokenId];
        require(_isOnSell(_sell), "ERROR: not on sell");
        return _sell.price;
    }
    
    function createSell(
        address _nftAddress,
        uint256 _tokenId,
        uint256 _price
    )
        external
        whenNotPaused
        canBeStoredWith128Bits(_price)
    {
        address _seller = msg.sender;
        require(_owns(_nftAddress, _seller, _tokenId), "ERROR: not owner");
        _escrow(_nftAddress, _seller, _tokenId);
        Sell memory _sell = Sell(
            _seller,
            uint128(_price),
            uint64(now)
        );
        _addSell(
            _nftAddress,
            _tokenId,
            _sell,
            _seller
        );
    }

    function buy(
        address _nftAddress,
        uint256 _tokenId
    )
        external
        payable
        whenNotPaused
    {
        _buy(_nftAddress, _tokenId, msg.value);
        _transfer(_nftAddress, msg.sender, _tokenId);
    }

    function cancelSell(address _nftAddress, uint256 _tokenId) external {
        Sell storage _sell = sells[_nftAddress][_tokenId];
        require(_isOnSell(_sell), "ERROR: not on sell");
        require(msg.sender == _sell.seller, "ERROR: you are not seller");
        _cancelSell(_nftAddress, _tokenId, _sell.seller);
    }

    function cancelAuctionWhenPaused(
        address _nftAddress,
        uint256 _tokenId
    )
        external
        whenPaused
        onlyOwner
    {
        Sell storage _sell = sells[_nftAddress][_tokenId];
        require(_isOnSell(_sell), "ERROR: not on sell");
        _cancelSell(_nftAddress, _tokenId, _sell.seller);
    }

    function _isOnSell(Sell storage _sell) internal view returns (bool) {
        return (_sell.startedAt > 0);
    }

    function _getNftContract(address _nftAddress) internal pure returns (IERC721Base) {
        IERC721Base candidateContract = IERC721Base(_nftAddress);
        // require(candidateContract.implementsERC721());
        return candidateContract;
    }

    function _owns(address _nftAddress, address _claimant, uint256 _tokenId) internal view returns (bool) {
        IERC721Base _nftContract = _getNftContract(_nftAddress);
        return (_nftContract.ownerOf(_tokenId) == _claimant);
    }

    function _addSell(
        address _nftAddress,
        uint256 _tokenId,
        Sell memory _sell,
        address _seller
    )
        internal
    {
        sells[_nftAddress][_tokenId] = _sell;
        SellCreated(
            _nftAddress,
            _tokenId,
            uint256(_sell.price),
            _seller
        );
    }

    function _removeSell(address _nftAddress, uint256 _tokenId) internal {
        delete sells[_nftAddress][_tokenId];
    }

    function _cancelSell(address _nftAddress, uint256 _tokenId, address _seller) internal {
        _removeSell(_nftAddress, _tokenId);
        _transfer(_nftAddress, _seller, _tokenId);
        SellCancelled(_nftAddress, _tokenId);
    }

    function _escrow(address _nftAddress, address _owner, uint256 _tokenId) internal {
        IERC721Base _nftContract = _getNftContract(_nftAddress);
        // It will throw if transfer fails
        _nftContract.transferFrom(_owner, address(this), _tokenId);
    }

    function _transfer(address _nftAddress, address _receiver, uint256 _tokenId) internal {
        IERC721Base _nftContract = _getNftContract(_nftAddress);
        // It will throw if transfer fails
        _nftContract.transferFrom(address(this), _receiver, _tokenId);
    }

    function _computeCut(uint256 _price) internal view returns (uint256) {
        return _price * marketCut / 10000;
    }

    function _buy(
        address _nftAddress,
        uint256 _tokenId,
        uint256 _bidAmount
    )
        internal
        returns (uint256)
    {
        Sell storage _sell = sells[_nftAddress][_tokenId];
        require(_isOnSell(_sell), "ERROR: not on sell");

        uint256 _price = _sell.price;
        require(_bidAmount >= _price, "ERROR: bid amount less than price");

        address payable _seller = payable(_sell.seller);

        _removeSell(_nftAddress, _tokenId);

        if (_price > 0) {
            uint256 _marketCut = _computeCut(_price);
            uint256 _sellerProceeds = _price - _marketCut;

            _seller.transfer(_sellerProceeds);
        }

        if(_bidAmount > _price){
            uint256 _bidExcess = _bidAmount - _price;
            msg.sender.transfer(_bidExcess);
        }

        // Tell the world!
        SellSuccessful(
            _nftAddress,
            _tokenId,
            _price,
            msg.sender
        );

        return _price;
    }
}