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
   
   
    // {
    //     path: '/',
    //     component: Home,
    //     name: 'Version',
    //     iconCls: 'fa fa-clone',
    //     children: [
    //         { path: '/Version', component: resolve=>require(['./views/version/Version.vue'],resolve), name: 'Version' },
    //         // { path: '/Account', component:  resolve=>require(['./views/thirdParty/Account.vue'],resolve), name: '对账列表' },
    //         // { path: '/page5', component: Page5, name: '页面5' }
    //     ]
    // },
    {
        path: '/',
        component: Home,
        name: 'Homepage',
        iconCls: 'fa fa-clone',
        children: [
            { path: '/Homepage', component: resolve=>require(['./views/homepage/Homepage.vue'],resolve), name: 'Homepage' },
            // { path: '/Account', component:  resolve=>require(['./views/thirdParty/Account.vue'],resolve), name: '对账列表' },
            // { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: 'Banner',
        iconCls: 'fa fa-clone',
        children: [
            { path: '/Banner', component: resolve=>require(['./views/Banner/banner.vue'],resolve), name: 'Banner' },
            // { path: '/Account', component:  resolve=>require(['./views/thirdParty/Account.vue'],resolve), name: '对账列表' },
            // { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: 'Windows',
        iconCls: 'fa fa-clone',
        children: [
            { path: '/Windows', component: resolve=>require(['./views/Windows/windows.vue'],resolve), name: 'Windows' },
            // { path: '/Account', component:  resolve=>require(['./views/thirdParty/Account.vue'],resolve), name: '对账列表' },
            // { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
    // {
    //     path: '/',
    //     component: Home,
    //     name: 'basesetting',
    //     iconCls: 'fa fa-clone',
    //     children: [
    //         { path: '/coins', component: resolve=>require(['./views/basesetting/coins.vue'],resolve), name: 'coins' },
    //         { path: '/Language', component: resolve=>require(['./views/basesetting/Language.vue'],resolve), name: 'Language' },
    //         // { path: '/Account', component:  resolve=>require(['./views/thirdParty/Account.vue'],resolve), name: '对账列表' },
    //         // { path: '/page5', component: Page5, name: '页面5' }
    //     ]
    // },
   
   
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