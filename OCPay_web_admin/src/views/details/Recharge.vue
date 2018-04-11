<template>
  <div>
    <h3>订单列表  <!--{{this.$store.state.count}}--></h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="22">
            <!-- <el-form-item label="所属平台">
                 <el-select v-model="formInline.platform" placeholder="请选择平台类型">
                      <el-option label="所有" value=""></el-option>
                    <el-option v-for="(item,index) in platform" :key="index" :label="item.merchantName" :value="item.id"></el-option>
                    
                </el-select>
            </el-form-item> -->
             <el-form-item label="交易状态">
                 <el-select v-model="formInline.orderStatus" placeholder="请选择交易状态"  @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                     <el-option label="等待" value="1"></el-option>
                    <el-option label="成功" value="2"></el-option>
                    <el-option label="失败" value="3"></el-option>
                </el-select>
            </el-form-item>
             <el-form-item label="订单类型">
                 <el-select v-model="formInline.transcationType" placeholder="请选择交易状态"  @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                     <el-option label="用户充值" value="1"></el-option>
                    <el-option label="充值gas" value="2"></el-option>
                    <el-option label="充值总账" value="3"></el-option>
                    <el-option label="第三方转账" value="4"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="交易时间">
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date" style="width: 100%;"  @change="onSubmit"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date1" style="width: 100%;"  @change="onSubmit"></el-date-picker>
                </el-col>
            </el-form-item>
            <el-form-item label="货币名称">
                <el-input v-model="formInline.coinName" placeholder="请输入货币名称"  @change="onSubmit"></el-input>
            </el-form-item>
             <el-form-item label="订单号" class="address">
                <el-input v-model="formInline.orderTxHash" placeholder="请输入订单号"  @change="onSubmit"></el-input>
            </el-form-item>
             <el-form-item label="收币地址" class="address">
                <el-input v-model="formInline.toAddress " placeholder="请输入订单号"  @change="onSubmit"></el-input>
            </el-form-item>
             <el-form-item label="汇币地址" class="address">
                <el-input v-model="formInline.fromAddress " placeholder="请输入订单号"  @change="onSubmit"></el-input>
            </el-form-item>
           
            
        </el-col>
        <el-col :span="2">
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" @click="onSubmitForm">清空</el-button>
        </el-col>
        <div style="clear:both;"></div>
    </el-form>  
    <hr/>
    <div class="table">
        <el-table
            :data="tableData"
            border
            max-height="550"
            style="width: 100%">
            <el-table-column
            label="序号" fixed="left" width="150px">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <!-- <el-table-column
            prop="coinId"
            label="交易编号">
            </el-table-column> -->
            <el-table-column
            prop="txHash"
            label="订单号" width="300px">
            </el-table-column>
             <el-table-column
            prop="fromAddress"
            label="汇币地址" width="260px">
            </el-table-column>
            <el-table-column
            prop="toAddress"
            label="收币地址" width="260px">
            </el-table-column>
            <el-table-column
            prop="coinName"
            label="货币类型" width="150px">
             </el-table-column>
             <el-table-column
            label="订单类型" width="150px">
            <template slot-scope="scope">
                   <span v-if="scope.row.transcationType==1">用户充值 </span>
                    <span v-if="scope.row.transcationType==2">充值gas</span>
                    <span v-if="scope.row.transcationType==3" >充值总账 </span>
                    <span v-if="scope.row.transcationType==4" >第三方转账 </span>
                </template>
            </el-table-column>
           
            
            <!-- <el-table-column
            label="交易状态">
            <template slot-scope="scope">
                   <span v-if="scope.row.orderStatus==1">等待 <el-button type="primary" @click="submit1(scope.row.txHash)">提交</el-button></span>
                    <span v-if="scope.row.orderStatus==2">成功</span>
                    <span v-if="scope.row.orderStatus==3" >失败 <el-button type="primary" @click="submit1(scope.row.txHash)">提交</el-button></span>
                </template>
            </el-table-column> -->
            <el-table-column
            prop="coinNum"
            label="货币数量" width="150px">
            </el-table-column>
            <el-table-column
            label="交易时间" width="170px">
                 <template slot-scope="scope">
                    <span >{{ scope.row.tradingTime | time }}</span>
                </template>
            </el-table-column>
             <el-table-column
            label="创建时间" width="170px">
                 <template slot-scope="scope">
                    <span >{{ scope.row.createdTime | time }}</span>
                </template>
            </el-table-column>
             <el-table-column
            label="更新时间" width="170px">
                 <template slot-scope="scope">
                    <span >{{ scope.row.updatedTime | time }}</span>
                </template>
            </el-table-column>
            <el-table-column
            label="回调时间" width="170px">
                 <template slot-scope="scope">
                    <span >{{ scope.row.callbackTime | time }}</span>
                </template>
            </el-table-column>
            <el-table-column
            label="回调状态" width="150px">
            <template slot-scope="scope">
                   <span v-if="scope.row.callbackStatus==0">无 </span>
                    <span v-if="scope.row.callbackStatus==1">回调中</span>
                    <span v-if="scope.row.callbackStatus==2" >回调成功 </span>
                </template>
            </el-table-column>
            <el-table-column
            prop="fee"
            label="矿工费" width="150px">
            </el-table-column>
            <!-- <el-table-column
            label="所属平台">
                <template slot-scope="scope">
                   <span v-for="(item,index) in platform" :key="index" v-if="item.id==scope.row.merchantId">{{item.merchantName}} </span>
                </template>
            </el-table-column> -->
            <!-- <el-table-column
            prop="address"
            label="交易类型">
            </el-table-column> -->
            <!-- <el-table-column
            prop="userid"
            label="用户编号">
            </el-table-column> -->
           
            
            
             
            <el-table-column
            prop="gasPrice"
            label="GAS费用" width="150px">
            </el-table-column>
            <el-table-column
            prop="usedGas"
            label="使用费用" width="150px">
            </el-table-column>
             <el-table-column
            prop="nonce"
            label="交易次数" width="150px">
            </el-table-column>
            <!-- <el-table-column
            prop="address"
            label="交易地址">
            </el-table-column> -->
        </el-table>
    </div>
    <div class="block" style="text-align:center;margin-top:20px;">
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pagesize"
          layout=" prev, pager, next"
          :total="totalCount">
        </el-pagination>
      </div>
    
  </div>
</template>

<script>
import { Recharge,becameStatus,allPlatform } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
        //   platform:'',
          orderStatus: '',
          date:'',
          date1:'',
          coinName:'',
          transcationType:'',
          orderTxHash:'',
          toAddress :'',
          fromAddress:''
        },
        platform:[],
        tableData: [],
        //默认每页数据量
        pagesize: 10,
        //当前页码
        currentPage: 1,
        //默认数据总数
        totalCount: 20,
        
        

    };
    },
    mounted(){
        allPlatform().then(data=>{
            // console.log(data.data);
            this.platform=data.data;
        })
        this.getcoin(this.currentPage);
    },
    methods: {
        onSubmitForm(){
            this.formInline.orderStatus=''
            this.formInline.date=''
            this.formInline.date1=''
            this.formInline.coinName=''
            this.formInline.transcationType=''
            this.formInline.orderTxHash=''
            this.formInline.toAddress=''
            this.formInline.fromAddress=''
            this.getcoin(this.currentPage);
        },
        onSubmit() {
           
            this.getcoin(this.currentPage);
        },
        getcoin(num){
            let date=new Date(this.formInline.date).getTime();
            let date1=new Date(this.formInline.date1).getTime()+60*60*24*1000;
            // console.log(this.formInline.date)
            let para='?pageNum='+num
            // if(this.formInline.platform!=''){
            //      para=para+'&platform='+this.formInline.platform
            // }
            if(this.formInline.orderStatus!=''){
                 para=para+'&orderStatus='+this.formInline.orderStatus
            }
            if(this.formInline.date!=''){
                 para=para+'&beginTradingTime='+date
            }
            if(this.formInline.date1!=''){
                 para=para+'&endTradingTime='+date1
            }
            if(this.formInline.transcationType!=''){
                 para=para+'&transcationType='+this.formInline.transcationType
            }
            if(this.formInline.coinName!=''){
                 para=para+'&coinName='+this.formInline.coinName
            }
            if(this.formInline.orderTxHash!=''){
                 para=para+'&txHash='+this.formInline.orderTxHash
            }
             if(this.formInline.toAddress !=''){
                 para=para+'&toAddress='+this.formInline.toAddress 
            }
             if(this.formInline.fromAddress!=''){
                 para=para+'&fromAddress='+this.formInline.fromAddress
            }
            // console.log(para)
            Recharge(para).then(data=>{
                // console.log(data.data.list)
                this.tableData=data.data.list;
                this.pagesize=data.data.per;
                this.totalCount=data.data.per*data.data.pages;
            })
        },
        
        handleCurrentChange(val){
            // console.log(val)
            this.currentPage=val;
            this.getcoin(this.currentPage);
        },
        submit1(val){
            // console.log(val)
            let para={
                "txHash":val
            }
            becameStatus(para).then(data=>{
                // console.log(data);
                this.$message({
                    message: data.data,
                    type: 'success'
                    });
                this.getcoin(this.currentPage);
            })
        }
       
    }
}

</script>
<style>
.wallet{
    padding: 20px;
}
.table{
    margin-top: 30px;
}
.line{
    text-align: right;
    padding-right: 5px;
}
.add{
    padding-left: 5px;
}
.add1{
    padding-left: 15px;
}
.secret{
    text-align: right;
    padding-right: 5px;
}
.erweima{
    padding-left: 20px;
}
.el-button+.el-button{
    margin-left: 2px;
}
.el-button+.el-button{
    margin-left: 0!important;
    margin-top: 5px;
}
</style>
