pragma solidity 0.6.12;

import "./IBEP20.sol";

/**
 * @title SafeBEP20
 * @dev Wrappers around BEP20 operations that throw on failure (when the token
 * contract returns false). Tokens that return no value (and instead revert or
 * throw on failure) are also supported, non-reverting calls are assumed to be
 * successful.
 * To use this library you can add a `using SafeBEP20 for IBEP20;` statement to your contract,
 * which allows you to call the safe operations as `token.safeTransfer(...)`, etc.
 */
library SafeBEP20 {
  function safeTransfer(IBEP20 token, address to, uint256 value) internal {
    assert(token.transfer(to, value));
  }

  function safeTransferFrom(IBEP20 token, address from, address to, uint256 value) internal {
    assert(token.transferFrom(from, to, value));
  }

  function safeApprove(IBEP20 token, address spender, uint256 value) internal {
    assert(token.approve(spender, value));
  }
}