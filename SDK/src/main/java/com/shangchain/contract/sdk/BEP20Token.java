package com.shangchain.contract.sdk;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class BEP20Token extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060006200001e6200013a565b600080546001600160a01b0319166001600160a01b0383169081178255604051929350917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a35060408051808201909152600d8082526c446f6f6d204865726f2044616f60981b60209092019182526200009e91600a916200013e565b506040805180820190915260038082526244484760e81b6020909201918252620000cb916009916200013e565b506008805460ff191660121790556b033b2e3c9fd0803ce80000006007819055336000818152600260209081526040808320859055805194855251929391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a3620001da565b3390565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200018157805160ff1916838001178555620001b1565b82800160010185558215620001b1579182015b82811115620001b157825182559160200191906001019062000194565b50620001bf929150620001c3565b5090565b5b80821115620001bf5760008155600101620001c4565b611a2380620001ea6000396000f3fe608060405234801561001057600080fd5b50600436106101735760003560e01c8063893d20e8116100de578063a9059cbb11610097578063d28d885211610071578063d28d885214610479578063dd62ed3e14610481578063f2fde38b146104af578063f3ae2415146104d557610173565b8063a9059cbb1461041f578063ac18de431461044b578063b09f12661461047157610173565b8063893d20e81461037c5780638da5cb5b146103a057806395d89b41146103a8578063a0712d68146103b0578063a219fdd6146103cd578063a457c2d7146103f357610173565b806332424aa31161013057806332424aa3146102cb57806339509351146102d3578063421a0a67146102ff57806342966c681461033157806370a082311461034e578063715018a61461037457610173565b806306fdde0314610178578063095ea7b3146101f557806318160ddd1461023557806323b872dd1461024f5780632d06177a14610285578063313ce567146102ad575b600080fd5b6101806104fb565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101ba5781810151838201526020016101a2565b50505050905090810190601f1680156101e75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102216004803603604081101561020b57600080fd5b506001600160a01b038135169060200135610591565b604080519115158252519081900360200190f35b61023d6105af565b60408051918252519081900360200190f35b6102216004803603606081101561026557600080fd5b506001600160a01b038135811691602081013590911690604001356105b5565b6102ab6004803603602081101561029b57600080fd5b50356001600160a01b031661063c565b005b6102b561072a565b6040805160ff9092168252519081900360200190f35b6102b5610733565b610221600480360360408110156102e957600080fd5b506001600160a01b03813516906020013561073c565b6102ab6004803603606081101561031557600080fd5b506001600160a01b03813516906020810135906040013561078a565b6102216004803603602081101561034757600080fd5b50356107f2565b61023d6004803603602081101561036457600080fd5b50356001600160a01b031661080e565b6102ab610829565b6103846108cb565b604080516001600160a01b039092168252519081900360200190f35b6103846108da565b6101806108e9565b610221600480360360208110156103c657600080fd5b503561094a565b61023d600480360360208110156103e357600080fd5b50356001600160a01b03166109f6565b6102216004803603604081101561040957600080fd5b506001600160a01b038135169060200135610b46565b6102216004803603604081101561043557600080fd5b506001600160a01b038135169060200135610bae565b6102ab6004803603602081101561046157600080fd5b50356001600160a01b0316610bc2565b610180610cad565b610180610d3b565b61023d6004803603604081101561049757600080fd5b506001600160a01b0381358116916020013516610d96565b6102ab600480360360208110156104c557600080fd5b50356001600160a01b0316610dc1565b610221600480360360208110156104eb57600080fd5b50356001600160a01b0316610e25565b600a8054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105875780601f1061055c57610100808354040283529160200191610587565b820191906000526020600020905b81548152906001019060200180831161056a57829003601f168201915b5050505050905090565b60006105a561059e610e44565b8484610e48565b5060015b92915050565b60075490565b60006105c2848484610f34565b610632846105ce610e44565b61062d856040518060600160405280602881526020016118b2602891396001600160a01b038a1660009081526003602052604081209061060c610e44565b6001600160a01b031681526020810191909152604001600020549190611261565b610e48565b5060019392505050565b610644610e44565b6000546001600160a01b03908116911614610694576040805162461bcd60e51b815260206004820181905260248201526000805160206118fb833981519152604482015290519081900360640190fd5b6001600160a01b0381166106d95760405162461bcd60e51b815260040180806020018281038252602681526020018061188c6026913960400191505060405180910390fd5b6040516001600160a01b038216907f3630096a7f9a158ab9fae41e86bfe31fd2202585a26a9668242672566dae028d90600090a26001600160a01b0316600090815260016020819052604090912055565b60085460ff1690565b60085460ff1681565b60006105a5610749610e44565b8461062d856003600061075a610e44565b6001600160a01b03908116825260208083019390935260409182016000908120918c1681529252902054906112f8565b610792610e44565b6000546001600160a01b039081169116146107e2576040805162461bcd60e51b815260206004820181905260248201526000805160206118fb833981519152604482015290519081900360640190fd5b6107ed838383611359565b505050565b60006108056107ff610e44565b8361148a565b5060015b919050565b6001600160a01b031660009081526002602052604090205490565b610831610e44565b6000546001600160a01b03908116911614610881576040805162461bcd60e51b815260206004820181905260248201526000805160206118fb833981519152604482015290519081900360640190fd5b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600080546001600160a01b0319169055565b60006108d56108da565b905090565b6000546001600160a01b031690565b60098054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156105875780601f1061055c57610100808354040283529160200191610587565b600060016000610958610e44565b6001600160a01b03166001600160a01b0316815260200190815260200160002054600114806109a6575061098a610e44565b6001600160a01b031661099b6108da565b6001600160a01b0316145b6109e5576040805162461bcd60e51b815260206004820181905260248201526000805160206118fb833981519152604482015290519081900360640190fd5b6108056109f0610e44565b8361157a565b6001600160a01b03811660009081526004602052604081205415610b2a576001600160a01b0382166000908152600460209081526040808320546005909252822054909190610a46904290611660565b90506000610a5582603c6116a2565b9050610a628360026116e4565b8111610b08576000610aab84610aa5610a8685610a808460026116e4565b90611660565b6001600160a01b038a16600090815260066020526040902054906116e4565b906116a2565b6001600160a01b0387166000908152600260205260409020549091508110610ada576000945050505050610809565b6001600160a01b038616600090815260026020526040902054610afd9082611660565b945050505050610809565b505050506001600160a01b038116600090815260026020526040902054610809565b506001600160a01b031660009081526002602052604090205490565b60006105a5610b53610e44565b8461062d856040518060600160405280602581526020016119646025913960036000610b7d610e44565b6001600160a01b03908116825260208083019390935260409182016000908120918d16815292529020549190611261565b60006105a5610bbb610e44565b8484610f34565b610bca610e44565b6000546001600160a01b03908116911614610c1a576040805162461bcd60e51b815260206004820181905260248201526000805160206118fb833981519152604482015290519081900360640190fd5b6001600160a01b038116610c5f5760405162461bcd60e51b815260040180806020018281038252602681526020018061188c6026913960400191505060405180910390fd5b6040516001600160a01b038216907f1e25ed4cabec84d314dc176241019653f237da01f2bdd3a10cb0f38b33da676390600090a26001600160a01b0316600090815260016020526040812055565b6009805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529291830182828015610d335780601f10610d0857610100808354040283529160200191610d33565b820191906000526020600020905b815481529060010190602001808311610d1657829003601f168201915b505050505081565b600a805460408051602060026001851615610100026000190190941693909304601f81018490048402820184019092528181529291830182828015610d335780601f10610d0857610100808354040283529160200191610d33565b6001600160a01b03918216600090815260036020908152604080832093909416825291909152205490565b610dc9610e44565b6000546001600160a01b03908116911614610e19576040805162461bcd60e51b815260206004820181905260248201526000805160206118fb833981519152604482015290519081900360640190fd5b610e228161173d565b50565b6001600160a01b03166000908152600160208190526040909120541490565b3390565b6001600160a01b038316610e8d5760405162461bcd60e51b81526004018080602001828103825260248152602001806118686024913960400191505060405180910390fd5b6001600160a01b038216610ed25760405162461bcd60e51b81526004018080602001828103825260228152602001806119cc6022913960400191505060405180910390fd5b6001600160a01b03808416600081815260036020908152604080832094871680845294825291829020859055815185815291517f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b9259281900390910190a3505050565b6001600160a01b038316610f795760405162461bcd60e51b81526004018080602001828103825260258152602001806118436025913960400191505060405180910390fd5b6001600160a01b038216610fbe5760405162461bcd60e51b81526004018080602001828103825260238152602001806119416023913960400191505060405180910390fd5b6001600160a01b0383166000908152600260205260409020548111156110155760405162461bcd60e51b815260040180806020018281038252602681526020018061191b6026913960400191505060405180910390fd5b6001600160a01b03831660009081526004602052604090205415611199576001600160a01b0383166000908152600460209081526040808320546005909252822054909190611065904290611660565b9050600061107482603c6116a2565b90508281116110c2576040805162461bcd60e51b81526020600482015260156024820152742122a819181d103a3930b739b332b91032b93937b960591b604482015290519081900360640190fd5b6110cd8360026116e4565b811161117b5761112a61110b84610aa56110ec85610a808460026116e4565b6001600160a01b038b16600090815260066020526040902054906116e4565b6001600160a01b03881660009081526002602052604090205490611660565b841115611176576040805162461bcd60e51b81526020600482015260156024820152742122a819181d103a3930b739b332b91032b93937b960591b604482015290519081900360640190fd5b611195565b6001600160a01b0386166000908152600460205260408120555b5050505b6111d68160405180606001604052806026815260200161191b602691396001600160a01b0386166000908152600260205260409020549190611261565b6001600160a01b03808516600090815260026020526040808220939093559084168152205461120590826112f8565b6001600160a01b0380841660008181526002602090815260409182902094909455805185815290519193928716927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef92918290030190a3505050565b600081848411156112f05760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b838110156112b557818101518382015260200161129d565b50505050905090810190601f1680156112e25780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b600082820183811015611352576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b9392505050565b6001600160a01b0383166000908152600460205260409020541561144d576001600160a01b03831660009081526004602090815260408083205460059092528220549091906113a9904290611660565b905060006113b882603c6116a2565b90506113c58360026116e4565b811161142f576001600160a01b0386166000908152600460205260409020541561142a576040805162461bcd60e51b81526020600482015260116024820152701091540c8c0e881a185cc81b1bd8dad959607a1b604482015290519081900360640190fd5b611449565b6001600160a01b0386166000908152600460205260408120555b5050505b6001600160a01b038316600090815260046020908152604080832084905560068252808320859055600590915290204290556107ed338484610f34565b6001600160a01b0382166114cf5760405162461bcd60e51b81526004018080602001828103825260218152602001806119896021913960400191505060405180910390fd5b61150c816040518060600160405280602281526020016119aa602291396001600160a01b0385166000908152600260205260409020549190611261565b6001600160a01b0383166000908152600260205260409020556007546115329082611660565b6007556040805182815290516000916001600160a01b038516917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9181900360200190a35050565b6001600160a01b0382166115d5576040805162461bcd60e51b815260206004820152601f60248201527f42455032303a206d696e7420746f20746865207a65726f206164647265737300604482015290519081900360640190fd5b6007546115e290826112f8565b6007556001600160a01b03821660009081526002602052604090205461160890826112f8565b6001600160a01b03831660008181526002602090815260408083209490945583518581529351929391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a35050565b600061135283836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f770000815250611261565b600061135283836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f0000000000008152506117dd565b6000826116f3575060006105a9565b8282028284828161170057fe5b04146113525760405162461bcd60e51b81526004018080602001828103825260218152602001806118da6021913960400191505060405180910390fd5b6001600160a01b0381166117825760405162461bcd60e51b815260040180806020018281038252602681526020018061188c6026913960400191505060405180910390fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b6000818361182c5760405162461bcd60e51b81526020600482018181528351602484015283519092839260449091019190850190808383600083156112b557818101518382015260200161129d565b50600083858161183857fe5b049594505050505056fe42455032303a207472616e736665722066726f6d20746865207a65726f206164647265737342455032303a20617070726f76652066726f6d20746865207a65726f20616464726573734f776e61626c653a206e6577206f776e657220697320746865207a65726f206164647265737342455032303a207472616e7366657220616d6f756e74206578636565647320616c6c6f77616e6365536166654d6174683a206d756c7469706c69636174696f6e206f766572666c6f774f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657242455032303a207472616e7366657220616d6f756e7420657863656564732062616c616e636542455032303a207472616e7366657220746f20746865207a65726f206164647265737342455032303a2064656372656173656420616c6c6f77616e63652062656c6f77207a65726f42455032303a206275726e2066726f6d20746865207a65726f206164647265737342455032303a206275726e20616d6f756e7420657863656564732062616c616e636542455032303a20617070726f766520746f20746865207a65726f2061646472657373a2646970667358221220c81e7b9e1318af6921b6411173147fa9064a57628a9ae0425d9660827787202064736f6c634300060c0033";

    public static final String FUNC__DECIMALS = "_decimals";

    public static final String FUNC__NAME = "_name";

    public static final String FUNC__SYMBOL = "_symbol";

    public static final String FUNC_ADDMANAGER = "addManager";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_ISMANAGER = "isManager";

    public static final String FUNC_LOCKTO = "lockTo";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REMOVEMANAGER = "removeManager";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SPENDABLE = "spendable";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event ADDMANAGER_EVENT = new Event("AddManager", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REMOVEMANAGER_EVENT = new Event("RemoveManager", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected BEP20Token(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BEP20Token(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BEP20Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BEP20Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AddManagerEventResponse> getAddManagerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDMANAGER_EVENT, transactionReceipt);
        ArrayList<AddManagerEventResponse> responses = new ArrayList<AddManagerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddManagerEventResponse typedResponse = new AddManagerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newManager = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddManagerEventResponse> addManagerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddManagerEventResponse>() {
            @Override
            public AddManagerEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDMANAGER_EVENT, log);
                AddManagerEventResponse typedResponse = new AddManagerEventResponse();
                typedResponse.log = log;
                typedResponse.newManager = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AddManagerEventResponse> addManagerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDMANAGER_EVENT));
        return addManagerEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<RemoveManagerEventResponse> getRemoveManagerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVEMANAGER_EVENT, transactionReceipt);
        ArrayList<RemoveManagerEventResponse> responses = new ArrayList<RemoveManagerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemoveManagerEventResponse typedResponse = new RemoveManagerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.manager = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RemoveManagerEventResponse> removeManagerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RemoveManagerEventResponse>() {
            @Override
            public RemoveManagerEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REMOVEMANAGER_EVENT, log);
                RemoveManagerEventResponse typedResponse = new RemoveManagerEventResponse();
                typedResponse.log = log;
                typedResponse.manager = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RemoveManagerEventResponse> removeManagerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REMOVEMANAGER_EVENT));
        return removeManagerEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> _decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> _name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> _symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC__SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addManager(String newManager) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDMANAGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newManager)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> isManager(String manager) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISMANAGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, manager)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> lockTo(String recipient, BigInteger amount, BigInteger lockMonthNum) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LOCKTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.generated.Uint256(lockMonthNum)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeManager(String manager) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEMANAGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, manager)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> spendable(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SPENDABLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static BEP20Token load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BEP20Token(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BEP20Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BEP20Token(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BEP20Token load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BEP20Token(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BEP20Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BEP20Token(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BEP20Token> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BEP20Token.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<BEP20Token> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BEP20Token.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BEP20Token> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BEP20Token.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BEP20Token> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BEP20Token.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AddManagerEventResponse extends BaseEventResponse {
        public String newManager;
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RemoveManagerEventResponse extends BaseEventResponse {
        public String manager;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
