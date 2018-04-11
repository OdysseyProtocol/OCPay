<template>
  <div>
    <h3>货币变动  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="19">
            <el-form-item label="所属平台">
                 <el-select v-model="formInline.platform" placeholder="请选择平台类型" @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                    <el-option v-for="(item,index) in platform" :key="index" :label="item.merchantName" :value="item.id"></el-option>
                    
                </el-select>
            </el-form-item>
             <el-form-item label="订单号" class="address">
                <el-input v-model="formInline.orderTxHash" placeholder="请输入订单号"  @change="onSubmit"></el-input>
            </el-form-item>
             <el-form-item label="余额变动类型">
                 <el-select v-model="formInline.changeType" placeholder="请选择变动类型"  @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                     <el-option label="用户充值" value="1"></el-option>
                    <el-option label="提到总账" value="2"></el-option>
                    <el-option label="用户消费" value="3"></el-option>
                    <el-option label="用户通过奖励获得" value="4"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="交易时间">
                <el-col :span="11">
                <el-date-picker type="datetime" placeholder="选择日期时间" v-model="formInline.date"  @change="onSubmit" style="width: 100%;"  default-time="['18:00:00']"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                <el-date-picker type="datetime" placeholder="选择日期时间" v-model="formInline.date1"  @change="onSubmit" style="width: 100%;" default-time="['18:00:00']"></el-date-picker>
                </el-col>
            </el-form-item>

            <el-form-item label="代币名称">
                <el-input v-model="formInline.coinName" placeholder="请输入代币名称"  @change="onSubmit"></el-input>
            </el-form-item>
            <el-form-item label="用户id">
                <el-input v-model="formInline.userId" placeholder="请输入用户id"  @change="onSubmit"></el-input>
            </el-form-item>
            <!-- <el-form-item label="钱包地址">
                <el-input v-model="formInline.address" placeholder="15sbadk6466"></el-input>
            </el-form-item> -->
        </el-col>
        <el-col :span="5">
            <el-button type="primary" @click="onSubmit">查询</el-button>
            <el-button type="primary" @click="onSubmitForm">清空</el-button>
            <el-button type="primary" @click="excel1">导出excel</el-button>
        </el-col>
        <div style="clear:both;"></div>
    </el-form>  
    <hr/>
    <div class="table">
        <el-table
            :data="tableData"
            border
            max-height="500"
            style="width: 100%">
            <el-table-column
            width="150px"
            label="序号" fixed="left">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <el-table-column
            prop="orderTxHash"
            label="订单号" width="600px">
            </el-table-column>
            <!-- <el-table-column
            prop="id"
            label="交易编号">
            </el-table-column> -->
            <el-table-column
            label="所属平台" width="150px">
                <template slot-scope="scope">
                   <span v-for="(item,index) in platform" :key="index" v-if="item.id==scope.row.merchantId">{{item.merchantName}} </span>
                </template>
            </el-table-column>
             <el-table-column
            prop="userid"
            label="用户id" width="150px">
            </el-table-column>
            <el-table-column
            label="余额变动类型" width="150px">
            <template slot-scope="scope">
                   <span v-if="scope.row.changeType==1">用户充值 </span>
                    <span v-if="scope.row.changeType==2">提到总账</span>
                    <span v-if="scope.row.changeType==3" >用户消费 </span>
                    <span v-if="scope.row.changeType==4" >用户通过奖励获得 </span>
                </template>
            </el-table-column>
            <el-table-column
            prop="changeNum"
            label="变动金额" width="150px">
            </el-table-column>
             <!-- <el-table-column
            prop="coinId"
            label="代币id">
            </el-table-column> -->
             <el-table-column
            prop="coinName"
            label="代币名称" width="150px">
            </el-table-column>
            <el-table-column
            label="创建时间" width="170px">
                 <template slot-scope="scope">
                    <span >{{ scope.row.createTime | time }}</span>
                </template>
            </el-table-column>
            <!-- <el-table-column
            prop="orderTxHash"
            label="orderTxHash">
            </el-table-column> -->
            
            <!-- <el-table-column
            prop="toAddress"
            label="收款钱包">
            </el-table-column>
            <el-table-column
            label="余额变动类型">
            <template slot-scope="scope">
                   <span v-if="scope.row.changeType==1">用户充值 </span>
                    <span v-if="scope.row.changeType==2">提到总账</span>
                    <span v-if="scope.row.changeType==3" >用户消费 </span>
                    <span v-if="scope.row.changeType==3" >用户通过奖励获得 </span>
                </template>
            </el-table-column>
            
            <el-table-column
            prop="usedGas"
            label="GAS费用">
            </el-table-column> -->
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
import { MoneyDetails,becameStatus,allPlatform,Excel } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
          platform:'',
          changeType: '',
          date:'',
          date1:'',
          coinName:'',
          userId:'',
          orderTxHash:''
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
        excel1(){
            // let date=new Date(this.formInline.date).getTime();
            // let date1=new Date(this.formInline.date1).getTime();
            // // console.log(date,date1)
            // let para={}
            // if(this.formInline.platform!=''){
            //      para.merchantId=this.formInline.platform
            // }
            // if(this.formInline.changeType!=''){
            //     para.changeType=this.formInline.changeType
            // }
            // if(this.formInline.date!=''){
            //      para.beginTradingTime=date
            // }
            // if(this.formInline.date1!=''){
            //      para.endTradingTime=date1
            // }
            // if(this.formInline.coinName!=''){
            //       para.coinName=this.formInline.coinName
            // }
            //  if(this.formInline.userId!=''){
            //       para.userid=this.formInline.userId
            // }
            // if(this.formInline.orderTxHash!=''){
            //       para.txHash=this.formInline.orderTxHash
            // }
            let date=new Date(this.formInline.date).getTime();
            let date1=new Date(this.formInline.date1).getTime();
            // console.log(date,date1)
            let para='?pageNum='+1
            if(this.formInline.platform!=''){
                 para=para+'&merchantId='+this.formInline.platform
            }
            if(this.formInline.changeType!=''){
                 para=para+'&changeType='+this.formInline.changeType
            }
            if(this.formInline.date!=''){
                 para=para+'&beginTime='+date
            }
            if(this.formInline.date1!=''){
                 para=para+'&endTime='+date1
            }
            if(this.formInline.coinName!=''){
                 para=para+'&coinName='+this.formInline.coinName
            }
             if(this.formInline.userId!=''){
                 para=para+'&userid='+this.formInline.userId
            }
            if(this.formInline.orderTxHash!=''){
                 para=para+'&txHash='+this.formInline.orderTxHash
            }
            Excel(para).then(data=>{
                
                var blob = new Blob([data])
                var a = document.createElement('a'); 
                 a.href = URL.createObjectURL(blob); 
                 a.download = "货币变动列表.xlsx";  
                 a.style.display = 'none';
                 document.body.appendChild(a);
                 a.click();
                 a.remove();
            })
        },
        onSubmitForm(){
            this.formInline.platform=''
            this.formInline.changeType=''
            this.formInline.date=''
            this.formInline.date1=''
            this.formInline.coinName=''
            this.formInline.orderTxHash=''
            this.formInline.userId=''
            this.getcoin(this.currentPage);
        },
        onSubmit() {
           
            this.getcoin(this.currentPage);
        },
        getcoin(num){
            let date=new Date(this.formInline.date).getTime();
            let date1=new Date(this.formInline.date1).getTime();
            // console.log(date,date1)
            let para='?pageNum='+num
            if(this.formInline.platform!=''){
                 para=para+'&merchantId='+this.formInline.platform
            }
            if(this.formInline.changeType!=''){
                 para=para+'&changeType='+this.formInline.changeType
            }
            if(this.formInline.date!=''){
                 para=para+'&beginTime='+date
            }
            if(this.formInline.date1!=''){
                 para=para+'&endTime='+date1
            }
            if(this.formInline.coinName!=''){
                 para=para+'&coinName='+this.formInline.coinName
            }
             if(this.formInline.userId!=''){
                 para=para+'&userid='+this.formInline.userId
            }
            if(this.formInline.orderTxHash!=''){
                 para=para+'&txHash='+this.formInline.orderTxHash
            }
            // console.log(para)
            MoneyDetails(para).then(data=>{
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
</style>
