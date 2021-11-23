package com.shangchain.contract.sdk;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint128;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
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
public class Marketplace extends Contract {
    public static final String BINARY = "60806040526000805460ff60a01b1916905534801561001d57600080fd5b506040516116ef3803806116ef8339818101604052604081101561004057600080fd5b5080516020909101516000610053610163565b600080546001600160a01b0319166001600160a01b0383169081178255604051929350917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a3506127108211156100de5760405162461bcd60e51b81526004018080602001828103825260268152602001806116c96026913960400191505060405180910390fd5b6001600160a01b038116610139576040805162461bcd60e51b815260206004820152601a60248201527f4552524f523a20746f6b656e2061646472657373206572726f72000000000000604482015290519081900360640190fd5b600191909155600280546001600160a01b0319166001600160a01b03909216919091179055610167565b3390565b611553806101766000396000f3fe6080604052600436106100fe5760003560e01c806372eb97d311610095578063aa6ecb5511610064578063aa6ecb5514610345578063bbb41e3e1461037e578063f2fde38b146103df578063f80395c714610412578063fff2813714610427576100fe565b806372eb97d3146102bf5780638456cb59146102e95780638da5cb5b146102fe578063a59ac6dd14610313576100fe565b806341314a1a116100d157806341314a1a146101b5578063449e815d1461021e5780635c975abb146102575780636bab231914610280576100fe565b806310fe9ae81461010d5780631ae6b6ee1461013e578063319ec7fd146101795780633f4ba83a146101a0575b34801561010a57600080fd5b50005b34801561011957600080fd5b5061012261043c565b604080516001600160a01b039092168252519081900360200190f35b34801561014a57600080fd5b506101776004803603604081101561016157600080fd5b506001600160a01b03813516906020013561044b565b005b34801561018557600080fd5b5061018e610584565b60408051918252519081900360200190f35b3480156101ac57600080fd5b5061017761058a565b3480156101c157600080fd5b506101ee600480360360408110156101d857600080fd5b506001600160a01b038135169060200135610643565b604080516001600160a01b0390941684526001600160801b03909216602084015282820152519081900360600190f35b34801561022a57600080fd5b5061018e6004803603604081101561024157600080fd5b506001600160a01b038135169060200135610682565b34801561026357600080fd5b5061026c610708565b604080519115158252519081900360200190f35b34801561028c57600080fd5b50610177600480360360608110156102a357600080fd5b506001600160a01b038135169060208101359060400135610718565b3480156102cb57600080fd5b50610177600480360360208110156102e257600080fd5b5035610872565b3480156102f557600080fd5b50610177610910565b34801561030a57600080fd5b506101226109d4565b6101776004803603606081101561032957600080fd5b506001600160a01b0381351690602081013590604001356109e3565b34801561035157600080fd5b506101776004803603604081101561036857600080fd5b506001600160a01b038135169060200135610a51565b34801561038a57600080fd5b506103b7600480360360408110156103a157600080fd5b506001600160a01b038135169060200135610b21565b604080516001600160a01b039094168452602084019290925282820152519081900360600190f35b3480156103eb57600080fd5b506101776004803603602081101561040257600080fd5b50356001600160a01b0316610bc5565b34801561041e57600080fd5b5061018e610c29565b34801561043357600080fd5b50610177610c2f565b6002546001600160a01b031690565b600054600160a01b900460ff1661049d576040805162461bcd60e51b81526020600482015260116024820152704552524f523a206973207061757365642160781b604482015290519081900360640190fd5b6104a5610d3e565b6000546001600160a01b039081169116146104f5576040805162461bcd60e51b815260206004820181905260248201526000805160206114d8833981519152604482015290519081900360640190fd5b6001600160a01b0382166000908152600360209081526040808320848452909152902061052181610d42565b610567576040805162461bcd60e51b815260206004820152601260248201527111549493d48e881b9bdd081bdb881cd95b1b60721b604482015290519081900360640190fd5b805461057f90849084906001600160a01b0316610d4b565b505050565b60015481565b610592610d3e565b6000546001600160a01b039081169116146105e2576040805162461bcd60e51b815260206004820181905260248201526000805160206114d8833981519152604482015290519081900360640190fd5b600054600160a01b900460ff16610634576040805162461bcd60e51b81526020600482015260116024820152704552524f523a206973207061757365642160781b604482015290519081900360640190fd5b6000805460ff60a01b19169055565b60036020908152600092835260408084209091529082529020805460018201546002909201546001600160a01b03909116916001600160801b03169083565b6001600160a01b038216600090815260036020908152604080832084845290915281206106ae81610d42565b6106f4576040805162461bcd60e51b815260206004820152601260248201527111549493d48e881b9bdd081bdb881cd95b1b60721b604482015290519081900360640190fd5b600101546001600160801b03169392505050565b600054600160a01b900460ff1681565b600054600160a01b900460ff161561076f576040805162461bcd60e51b81526020600482015260156024820152744552524f523a206973206e6f74207061757365642160581b604482015290519081900360640190fd5b806001600160801b0381106107c2576040805162461bcd60e51b815260206004820152601460248201527311549493d48e881d985b1d59481a5b9d985b1a5960621b604482015290519081900360640190fd5b336107ce858286610d9b565b610812576040805162461bcd60e51b815260206004820152601060248201526f22a92927a91d103737ba1037bbb732b960811b604482015290519081900360640190fd5b61081d858286610e36565b61082561144d565b6040518060600160405280836001600160a01b03168152602001856001600160801b031681526020014267ffffffffffffffff16815250905061086a86868385610eba565b505050505050565b61087a610d3e565b6000546001600160a01b039081169116146108ca576040805162461bcd60e51b815260206004820181905260248201526000805160206114d8833981519152604482015290519081900360640190fd5b61271081111561090b5760405162461bcd60e51b81526004018080602001828103825260268152602001806114f86026913960400191505060405180910390fd5b600155565b610918610d3e565b6000546001600160a01b03908116911614610968576040805162461bcd60e51b815260206004820181905260248201526000805160206114d8833981519152604482015290519081900360640190fd5b600054600160a01b900460ff16156109bf576040805162461bcd60e51b81526020600482015260156024820152744552524f523a206973206e6f74207061757365642160581b604482015290519081900360640190fd5b6000805460ff60a01b1916600160a01b179055565b6000546001600160a01b031690565b600054600160a01b900460ff1615610a3a576040805162461bcd60e51b81526020600482015260156024820152744552524f523a206973206e6f74207061757365642160581b604482015290519081900360640190fd5b610a45838383610f63565b5061057f8333846111b8565b6001600160a01b03821660009081526003602090815260408083208484529091529020610a7d81610d42565b610ac3576040805162461bcd60e51b815260206004820152601260248201527111549493d48e881b9bdd081bdb881cd95b1b60721b604482015290519081900360640190fd5b80546001600160a01b03163314610567576040805162461bcd60e51b815260206004820152601960248201527f4552524f523a20796f7520617265206e6f742073656c6c657200000000000000604482015290519081900360640190fd5b6001600160a01b0382166000908152600360209081526040808320848452909152812081908190610b5181610d42565b610b97576040805162461bcd60e51b815260206004820152601260248201527111549493d48e881b9bdd081bdb881cd95b1b60721b604482015290519081900360640190fd5b805460018201546002909201546001600160a01b03909116976001600160801b039092169650945092505050565b610bcd610d3e565b6000546001600160a01b03908116911614610c1d576040805162461bcd60e51b815260206004820181905260248201526000805160206114d8833981519152604482015290519081900360640190fd5b610c268161121e565b50565b60015490565b610c37610d3e565b6000546001600160a01b03908116911614610c87576040805162461bcd60e51b815260206004820181905260248201526000805160206114d8833981519152604482015290519081900360640190fd5b600254600090610c9f906001600160a01b03166112be565b90506000816001600160a01b03166370a08231306040518263ffffffff1660e01b815260040180826001600160a01b0316815260200191505060206040518083038186803b158015610cf057600080fd5b505afa158015610d04573d6000803e3d6000fd5b505050506040513d6020811015610d1a57600080fd5b50519050610d3a610d296109d4565b6001600160a01b03841690836112c1565b5050565b3390565b60020154151590565b610d55838361134a565b610d608382846111b8565b60405182906001600160a01b038516907f37d267acf7fdf1a80cfbeb488e238947fbd27bc0f161e298bb66f9fabce7a8d790600090a3505050565b600080610da7856112be565b9050836001600160a01b0316816001600160a01b0316636352211e856040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610df757600080fd5b505afa158015610e0b573d6000803e3d6000fd5b505050506040513d6020811015610e2157600080fd5b50516001600160a01b03161495945050505050565b6000610e41846112be565b604080516323b872dd60e01b81526001600160a01b038681166004830152306024830152604482018690529151929350908316916323b872dd9160648082019260009290919082900301818387803b158015610e9c57600080fd5b505af1158015610eb0573d6000803e3d6000fd5b5050505050505050565b6001600160a01b038481166000818152600360209081526040808320888452825291829020865181546001600160a01b031916908616178155868201516001820180546001600160801b0319166001600160801b03909216918217905587840151600290920191909155825190815293851690840152805186937f752c13fed7e7738bb1894c457954ca6bb3901bcd7e1dcac2c950986bb6fc172b92908290030190a350505050565b6001600160a01b03831660009081526003602090815260408083208584529091528120610f8f81610d42565b610fd5576040805162461bcd60e51b815260206004820152601260248201527111549493d48e881b9bdd081bdb881cd95b1b60721b604482015290519081900360640190fd5b60018101546001600160801b0316808410156110225760405162461bcd60e51b81526004018080602001828103825260218152602001806114946021913960400191505060405180910390fd5b60025460009061103a906001600160a01b03166112be565b905060003390506000826001600160a01b03166370a08231836040518263ffffffff1660e01b815260040180826001600160a01b0316815260200191505060206040518083038186803b15801561109057600080fd5b505afa1580156110a4573d6000803e3d6000fd5b505050506040513d60208110156110ba57600080fd5b50519050868110156110fd5760405162461bcd60e51b81526004018080602001828103825260238152602001806114b56023913960400191505060405180910390fd5b60006111076109d4565b86549091506001600160a01b031661111f8b8b61134a565b851561116157600061113087611395565b905080870361114a6001600160a01b03881687308b6113ad565b61115e6001600160a01b03881684836112c1565b50505b604080518781526001600160a01b03868116602083015282518d93918f16927fe307500a0b615a892293584b60d92e9922b95beb8f67285b70b6871a58aa842b92908290030190a350939998505050505050505050565b60006111c3846112be565b604080516323b872dd60e01b81523060048201526001600160a01b038681166024830152604482018690529151929350908316916323b872dd9160648082019260009290919082900301818387803b158015610e9c57600080fd5b6001600160a01b0381166112635760405162461bcd60e51b815260040180806020018281038252602681526020018061146e6026913960400191505060405180910390fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b90565b826001600160a01b031663a9059cbb83836040518363ffffffff1660e01b815260040180836001600160a01b0316815260200182815260200192505050602060405180830381600087803b15801561131857600080fd5b505af115801561132c573d6000803e3d6000fd5b505050506040513d602081101561134257600080fd5b505161057f57fe5b6001600160a01b03909116600090815260036020908152604080832093835292905290812080546001600160a01b03191681556001810180546001600160801b031916905560020155565b60006127106001548302816113a657fe5b0492915050565b836001600160a01b03166323b872dd8484846040518463ffffffff1660e01b815260040180846001600160a01b03168152602001836001600160a01b031681526020018281526020019350505050602060405180830381600087803b15801561141557600080fd5b505af1158015611429573d6000803e3d6000fd5b505050506040513d602081101561143f57600080fd5b505161144757fe5b50505050565b60408051606081018252600080825260208201819052918101919091529056fe4f776e61626c653a206e6577206f776e657220697320746865207a65726f20616464726573734552524f523a2062696420616d6f756e74206c657373207468616e2070726963654552524f523a2062616c616e6365206c656573207468656e2062696420616d6f756e744f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65724552524f523a206d61726b657420637574206d757374206c657373207468616e203130303030a26469706673582212201ae255e1d0d853554758a89f7f05c5f718f7572e64a97146068cae4e1e35c0c564736f6c634300060c00334552524f523a206d61726b657420637574206d757374206c657373207468616e203130303030";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_CANCELAUCTIONWHENPAUSED = "cancelAuctionWhenPaused";

    public static final String FUNC_CANCELSELL = "cancelSell";

    public static final String FUNC_CREATESELL = "createSell";

    public static final String FUNC_GETMARKETCUT = "getMarketCut";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_GETSELL = "getSell";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_MARKETCUT = "marketCut";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RECLAIMTOKEN = "reclaimToken";

    public static final String FUNC_SELLS = "sells";

    public static final String FUNC_SETMARKETCUT = "setMarketCut";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SELLCANCELLED_EVENT = new Event("SellCancelled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event SELLCREATED_EVENT = new Event("SellCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event SELLSUCCESSFUL_EVENT = new Event("SellSuccessful", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Marketplace(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Marketplace(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Marketplace(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Marketplace(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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

    public List<SellCancelledEventResponse> getSellCancelledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SELLCANCELLED_EVENT, transactionReceipt);
        ArrayList<SellCancelledEventResponse> responses = new ArrayList<SellCancelledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SellCancelledEventResponse typedResponse = new SellCancelledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._nftAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SellCancelledEventResponse> sellCancelledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SellCancelledEventResponse>() {
            @Override
            public SellCancelledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SELLCANCELLED_EVENT, log);
                SellCancelledEventResponse typedResponse = new SellCancelledEventResponse();
                typedResponse.log = log;
                typedResponse._nftAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SellCancelledEventResponse> sellCancelledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SELLCANCELLED_EVENT));
        return sellCancelledEventFlowable(filter);
    }

    public List<SellCreatedEventResponse> getSellCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SELLCREATED_EVENT, transactionReceipt);
        ArrayList<SellCreatedEventResponse> responses = new ArrayList<SellCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SellCreatedEventResponse typedResponse = new SellCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._nftAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._seller = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SellCreatedEventResponse> sellCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SellCreatedEventResponse>() {
            @Override
            public SellCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SELLCREATED_EVENT, log);
                SellCreatedEventResponse typedResponse = new SellCreatedEventResponse();
                typedResponse.log = log;
                typedResponse._nftAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._seller = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SellCreatedEventResponse> sellCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SELLCREATED_EVENT));
        return sellCreatedEventFlowable(filter);
    }

    public List<SellSuccessfulEventResponse> getSellSuccessfulEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SELLSUCCESSFUL_EVENT, transactionReceipt);
        ArrayList<SellSuccessfulEventResponse> responses = new ArrayList<SellSuccessfulEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SellSuccessfulEventResponse typedResponse = new SellSuccessfulEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._nftAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._totalPrice = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SellSuccessfulEventResponse> sellSuccessfulEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SellSuccessfulEventResponse>() {
            @Override
            public SellSuccessfulEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SELLSUCCESSFUL_EVENT, log);
                SellSuccessfulEventResponse typedResponse = new SellSuccessfulEventResponse();
                typedResponse.log = log;
                typedResponse._nftAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._totalPrice = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SellSuccessfulEventResponse> sellSuccessfulEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SELLSUCCESSFUL_EVENT));
        return sellSuccessfulEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(String _nftAddress, BigInteger _tokenId, BigInteger _buyAmount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _nftAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_buyAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelAuctionWhenPaused(String _nftAddress, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELAUCTIONWHENPAUSED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _nftAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelSell(String _nftAddress, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELSELL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _nftAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createSell(String _nftAddress, BigInteger _tokenId, BigInteger _price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATESELL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _nftAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getMarketCut() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETMARKETCUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPrice(String _nftAddress, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _nftAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> getSell(String _nftAddress, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSELL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _nftAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> getTokenAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOKENADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> marketCut() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MARKETCUT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> reclaimToken() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RECLAIMTOKEN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> sells(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SELLS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint128>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setMarketCut(BigInteger _marketCut) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETMARKETCUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketCut)), 
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

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Marketplace load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Marketplace(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Marketplace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Marketplace(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Marketplace load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Marketplace(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Marketplace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Marketplace(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Marketplace> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _marketCut, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketCut), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Marketplace.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Marketplace> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _marketCut, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketCut), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Marketplace.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Marketplace> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _marketCut, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketCut), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Marketplace.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Marketplace> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _marketCut, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_marketCut), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Marketplace.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class SellCancelledEventResponse extends BaseEventResponse {
        public String _nftAddress;

        public BigInteger _tokenId;
    }

    public static class SellCreatedEventResponse extends BaseEventResponse {
        public String _nftAddress;

        public BigInteger _tokenId;

        public BigInteger _price;

        public String _seller;
    }

    public static class SellSuccessfulEventResponse extends BaseEventResponse {
        public String _nftAddress;

        public BigInteger _tokenId;

        public BigInteger _totalPrice;

        public String _buyer;
    }
}
