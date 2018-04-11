<template>
  <div>
    <h3>第三方转账  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="22">
            <el-form-item label="所属平台">
                 <!-- <el-input v-model="formInline.platform" placeholder="填写平台名字"></el-input> -->
                 <el-select v-model="formInline.platform" placeholder="请选择平台类型"  @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                    <el-option v-for="(item,index) in platform" :key="index" :label="item.merchantName" :value="item.id"></el-option>
                    <!-- <el-option label="Odyssey" value="Odyssey"></el-option> -->
                    
                </el-select>
            </el-form-item>
             <el-form-item label="交易状态">
                 <el-select v-model="formInline.state" placeholder="请选择交易状态"  @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                     <el-option label="等待" value="1"></el-option>
                    <el-option label="成功" value="2"></el-option>
                    <el-option label="失败" value="3"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="交易时间">
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date"  @change="onSubmit" style="width: 100%;" ></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date1"  @change="onSubmit" style="width: 100%;"></el-date-picker>
                </el-col>
            </el-form-item>

            <el-form-item class="address" label="转账地址">
                <el-input v-model="formInline.fromAddress" placeholder="请输入转账地址"  @change="onSubmit"></el-input>
            </el-form-item>
            <el-form-item class="address" label="收币地址">
                <el-input v-model="formInline.toAddress" placeholder="请输入收币地址"  @change="onSubmit"></el-input>
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
            max-height="500"
            style="width: 100%">
            <el-table-column
            label="序号" fixed="left" width="150px">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <el-table-column
            prop="orderTxHash"
            label="订单号" width="300px">
            </el-table-column>
             <el-table-column
            prop="fromAddress"
            label="转账地址" width="260px">
            </el-table-column>
             <el-table-column
            prop="toAddress"
            label="收币地址" width="260px">
            </el-table-column>
             <el-table-column
            prop="coinId"
            label="代币id" width="150px">
            </el-table-column>
             <el-table-column
            prop="amount"
            label="转账数量" width="150px">
            </el-table-column>
             <el-table-column
            prop="orderId"
            label="第三方订单id" width="170px">
            </el-table-column>
             <!-- <el-table-column
            prop="merchantId"
            label="第三方商户id">
            
            </el-table-column> -->
             <el-table-column
            label="所属平台" width="150px">
                <template slot-scope="scope">
                   <span v-for="(item,index) in platform" :key="index" v-if="item.id==scope.row.merchantId">{{item.merchantName}} </span>
                </template>
            </el-table-column>
            
             <el-table-column
            label="转账状态" width="150px">
                <template slot-scope="scope">
                   <span v-if="scope.row.status==1">等待 </span>
                    <span v-if="scope.row.status==2">成功</span>
                    <span v-if="scope.row.status==3">失败</span>
                </template>
            </el-table-column>
             <el-table-column
            label="转账时间" width="170px">
                <template slot-scope="scope">
                    <span >{{ scope.row.createdAt | time }}</span>
                </template>
            </el-table-column>
            <el-table-column
            label="修改时间" width="170px">
                <template slot-scope="scope">
                    <span >{{ scope.row.updatedAt | time }}</span>
                </template>
            </el-table-column>
            <!-- <el-table-column
            prop="address"
            label="交易类型">
            </el-table-column> -->
            <!-- <el-table-column
            prop="userid"
            label="用户编号">
            </el-table-column>
            <el-table-column
            prop="fromAddress"
            label="发款钱包">
            </el-table-column>
            <el-table-column
            prop="toAddress"
            label="收款钱包">
            </el-table-column>
            <el-table-column
            label="交易状态">
            <template slot-scope="scope">
                   <span v-if="scope.row.orderStatus==1">等待 <el-button type="primary" @click="submit1(scope.row.txHash)">提交</el-button></span>
                    <span v-if="scope.row.orderStatus==2">成功</span>
                    <span v-if="scope.row.orderStatus==3" >失败 <el-button type="primary" @click="submit1(scope.row.txHash)">提交</el-button></span>
                </template>
            </el-table-column>
            <el-table-column
            label="交易时间">
                 <template slot-scope="scope">
                    <span >{{ scope.row.tradingTime | time }}</span>
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
import { PlatformDetails,becameStatus,allPlatform } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
          platform:'',
          state: '',
          date:'',
          date1:'',
          toAddress:'',
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
            this.formInline.platform=''
            this.formInline.state=''
            this.formInline.date=''
            this.formInline.date1=''
            this.formInline.fromAddress=''
             this.formInline.toAddress=''
            this.getcoin(this.currentPage);
        },
        onSubmit() {
           
            this.getcoin(this.currentPage);
        },
        getcoin(num){
            let date=new Date(this.formInline.date).getTime();
            let date1=new Date(this.formInline.date1).getTime()+60*60*24*1000;
            // console.log(date,date1)
            let para='?pageNum='+num
            if(this.formInline.platform!=''){
                 para=para+'&merchantId='+this.formInline.platform
            }
            if(this.formInline.state!=''){
                 para=para+'&status='+this.formInline.state
            }
            if(this.formInline.date!=''){
                 para=para+'&beginTime='+date
            }
            if(this.formInline.date1!=''){
                 para=para+'&endTime='+date1
            }
            if(this.formInline.fromAddress!=''){
                 para=para+'&fromAddress='+this.formInline.fromAddress
            }
            if(this.formInline.toAddress!=''){
                 para=para+'&toAddress='+this.formInline.toAddress
            }
            // console.log(para)
            PlatformDetails(para).then(data=>{
                console.log(data.data.list)
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
            console.log(val)
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
