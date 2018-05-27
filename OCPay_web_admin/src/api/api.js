import axios from 'axios';

let base ='/get'+ '/api/ocpay/v1/';
// 登陆
export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };
// 创建用户
export const addLogin = params => { return axios.post(`${base}/add-admin`, params).then(res => res.data); };
// 修改密码
export const becamePass = params => { return axios.post(`${base}/admin-pwd`, params).then(res => res.data); };
//账户列表
export const AccountList = params => { return axios.get(`${base}/user/users`+params).then(res => res.data); };

//所有平台
export const allPlatform = params => { return axios.get(`${base}/merchant-info/list-no-page`).then(res => res.data); };

 
//货币管理
// 请求货币列表
export const getCurrency = params => { return axios.get(`${base}/coin-info?pageNum=`+params).then(res => res.data); };
// 新增货币
export const addCurrency = params => { return axios.post(`${base}/coin-info`, params).then(res => res.data); };
// 编辑货币信息
export const becameCurrency = params => { return axios.put(`${base}/coin-info`, params).then(res => res.data); };

// 钱包组管理
//请求钱包列表
export const getWallet = params => { return axios.get(`${base}/wallet-group`+params).then(res => res.data); };
// 新建钱包组
export const addWallet = params => { return axios.post(`${base}/wallet-group`,params).then(res => res.data); };
//编辑钱包组
export const becameWallet = params => { return axios.put(`${base}/wallet-group`,params).then(res => res.data); };
//删除钱包组
export const delWallet = params => { return axios.post(`${base}/wallet-group/del`,params).then(res => res.data); };
//新增钱包地址
export const addWalletAddress = params => { return axios.put(`${base}/wallet-group/detail/add`,params).then(res => res.data); };
//删除钱包地址
export const delWalletAddress = params => { return axios.post(`${base}/wallet-group/detail/remove`,params).then(res => res.data); };

//第三方管理
//查询第三方
export const getThird = params => { return axios.get(`${base}/merchant-info/list?pageNum=`+params).then(res => res.data); };
//新增第三方
export const addThird = params => { return axios.post(`${base}/merchant-info`,params).then(res => res.data); };
//编辑第三方
export const becameThird = params => { return axios.put(`${base}/merchant-info`,params).then(res => res.data); };
//查询可添加第三方钱包组
export const getThirdWallet = params => { return axios.get(`${base}/wallet-group/exclusions`+params).then(res => res.data); };
//删除第三方钱包组
export const delThirdWallet = params => { return axios.post(`${base}/merchant-info/del`,params).then(res => res.data); };
//添加第三方钱包组
export const addThirdWallet = params => { return axios.post(`${base}/merchant-info/wallet-group-add`,params).then(res => res.data); };
//查询第三方已有钱包组
export const getThirdWalletAgo = params => { return axios.get(`${base}/merchant-info/wallet-group`+params).then(res => res.data); };
//生成apikey 
export const apiKey = params => { return axios.post(`${base}/merchant-info/apikey`).then(res => res.data); };
//security 
export const security = params => { return axios.post(`${base}/merchant-info/secretKey`).then(res => res.data); };
//第三方对账列表
export const Account = params => { return axios.get(`${base}/merchant-info/bill-list`+params).then(res => res.data); };

//导出excel
export const Excel = params => {
    let instance = axios.create({
        headers: {'Authorization': `Bearer ${sessionStorage.getItem('user')}`},
        responseType:'blob'
      });
    return instance.get(`${base}/user-coin-log/export`+params).then(res => res.data); 
};



//用户管理
//用户列表
export const userList = params => { return axios.get(`${base}/user/walletinfo`+params).then(res => res.data); };
// 用户余额
export const userBalance = params => { return axios.get(`${base}/user/coin-balance`+params).then(res => res.data); };

// 交易管理
//交易明细
export const Details = params => { return axios.get(`${base}/recharge-list`+params).then(res => res.data); }; //目前没有
//错误信息
export const ErrorDetails = params => { return axios.get(`${base}/defeat-order-log`+params).then(res => res.data); }; 
//充值明细
export const Recharge = params => { return axios.get(`${base}/transaction-order`+params).then(res => res.data); };
//改变交易状态
export const becameStatus = params => { return axios.put(`${base}/confirm-order`,params).then(res => res.data); };
//货币变动
export const MoneyDetails = params => { return axios.get(`${base}/user-coin-log`+params).then(res => res.data); }; 
//第三方转账
export const PlatformDetails = params => { return axios.get(`${base}/platform-transfer`+params).then(res => res.data); }; 
//油费明细
export const GasDetails = params => { return axios.get(`${base}/gas_transaction_log`+params).then(res => res.data); }; 


// export const getUserList = params => { return axios.get(`${base}/user/list`, { params: params }); };

// export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

// export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

// export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

// export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

// export const addUser = params => { return axios.get(`${base}/user/add`, { params: params }); };
 

// Homepage
// Homepage
export const Homepage = params => { return axios.post(`${base}/get-homePage`,params).then(res => res.data); };
// HomepageEdit
export const HomepageEdit = params => { return axios.post(`${base}/edit-homePage`,params).then(res => res.data); };
// HomepageDelete
export const HomepageDelete = params => { return axios.post(`${base}/delete-homePage`,params).then(res => res.data); };
// add
export const HomepageAdd = params => { return axios.post(`${base}/add-homePage`,params).then(res => res.data); };

// Banner
// Banner
export const Banner= params => { return axios.post(`${base}/get-advertisment`,params).then(res => res.data); };
// BannerEdit
export const BannerEdit= params => { return axios.post(`${base}/edit-advertisment`,params).then(res => res.data); };
// BannerDelete
export const BannerDelete= params => { return axios.post(`${base}/delete-advertisment`,params).then(res => res.data); };
// BannerAdd
export const BannerAdd= params => { return axios.post(`${base}/add-advertisment`,params).then(res => res.data); };
// get-hompageid
export const BannerGet= params => { return axios.post(`${base}/get-homePageId`,params).then(res => res.data); };



let baseUrl='/get'+ '/api/ocpay/upload/v1/';
// 上传图片
export const upload= params => { return axios.post(`${baseUrl}/file`,params).then(res => res.data); };









