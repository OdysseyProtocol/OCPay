import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
// import Main from './views/Main.vue'
// import Table from './views/nav1/Table.vue'
// import Form from './views/nav1/Form.vue'
// import user from './views/nav1/user.vue'
// import Page4 from './views/nav2/Page4.vue'
// import Add from './views/nav1/Add.vue'
// 第三方管理
// import thirdParty from './views/thirdParty/thirdParty.vue'
// import Account from './views/thirdParty/Account.vue'
// // 交易管理
// import Details from './views/details/Details.vue'
// //货币变动
// import Change from './views/details/Change.vue'
// // 充值明细
// import Recharge from './views/details/Recharge.vue'

// //油费明细
// import Gas from './views/details/Gas.vue'
// //错误订单
// import Error from './views/details/Error.vue'
// //第三方转账
// import Platform from './views/details/Platform.vue'

// // 用户管理
// // 用户钱包
// import UserList from './views/userAdmin/UserList.vue'
// // 用户货币余额
// import UserBalance from './views/userAdmin/UserBalance.vue'
// // 钱包
import Wallet from './views/wallet/Wallet.vue'
// // 货币管理
// import Currency from './views/Currency/Currency.vue'
//修改密码
// import User from './views/User/User.vue'
// //新建用户
// import AddUser from './views/User/AddUser.vue'
// // 账户列表
// import AccountList from './views/User/AccountList.vue'


let became = true;

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
   
    {
        path: '/',
        component: Home,
        name: '第三方管理',
        iconCls: 'fa fa-clone',
        children: [
            { path: '/thirdParty', component: resolve=>require(['./views/thirdParty/thirdParty.vue'],resolve), name: '第三方列表' },
            { path: '/Account', component:  resolve=>require(['./views/thirdParty/Account.vue'],resolve), name: '对账列表' },
            // { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '交易管理',
        iconCls: 'fa fa-handshake-o',
        children: [
            // { path: '/Details', component: Details, name: '交易明细' },
            { path: '/Change', component: resolve=>require(['./views/details/Change.vue'],resolve), name: '货币变动' },
            { path: '/Recharge', component: resolve=>require(['./views/details/Recharge.vue'],resolve), name: '订单列表' },
            { path: '/Gas', component: resolve=>require(['./views/details/Gas.vue'],resolve), name: '邮费明细' },
            { path: '/Error', component: resolve=>require(['./views/details/Error.vue'],resolve), name: '错误订单' },
            { path: '/Platform', component: resolve=>require(['./views/details/Platform.vue'],resolve), name: '第三方转账' },
            // { path: '/Withdraw', component: Withdraw, name: '提现明细' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '用户管理',
        iconCls: 'fa fa-address-book',
        children: [
            { path: '/UserList', component: resolve=>require(['./views/userAdmin/UserList.vue'],resolve), name: '用户钱包' },
            { path: '/UserBalance', component: resolve=>require(['./views/userAdmin/UserBalance.vue'],resolve), name: '用户货币余额' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '钱包组管理',
        iconCls: 'fa fa-credit-card',
        children: [
            { path: '/Wallet', component: Wallet, name: '钱包组列表' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '货币管理',
        iconCls: 'fa fa-btc',
        children: [
            { path: '/Currency', component: resolve=>require(['./views/Currency/Currency.vue'],resolve), name: '货币列表' },
            // { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },
    // {
    //     path: '/',
    //     component: Home,
    //     name: '设置',
    //     iconCls: 'el-icon-setting',
    //     children: [
    //         { path: '/User', component: User, name: '修改密码' },
    //         // { path: '/echarts', component: echarts, name: 'echarts' }
    //     ]
    // },
    {
        path: '/',
        component: Home,
        name: '账户管理',
        iconCls: 'el-icon-setting',
        children: [
            { path: '/AddUser', component: resolve=>require(['./views/User/AddUser.vue'],resolve), name: '新建用户' },
            { path: '/User', component: resolve=>require(['./views/User/User.vue'],resolve), name: '修改密码' },
            { path: '/AccountList', component: resolve=>require(['./views/User/AccountList.vue'],resolve), name: '账户列表' },
            // { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },
    {
        path: '*',
        hidden: became,
        redirect: { path: '/404' }
    }
];

export default routes;