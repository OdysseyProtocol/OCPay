<template>
  <div>
    <h3>邮费明细  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="22">
            <el-form-item class="address" label="订单号">
                <el-input v-model="formInline.orderTxHash" placeholder="请输入订单号"  @change="onSubmit"></el-input>
            </el-form-item>
             <el-form-item class="address" label="邮费地址">
                <el-input  v-model="formInline.fromAddress" placeholder="请输入邮费地址"  @change="onSubmit"></el-input>
            </el-form-item>
             <el-form-item class="address" label="用户地址">
                <el-input v-model="formInline.toAddress" placeholder="请输入用户地址"  @change="onSubmit"></el-input>
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
            width="150px"
            label="序号" fixed="left">
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
            label="邮费地址" width="260px">
            </el-table-column>
             <el-table-column
            prop="toAddress"
            label="用户地址" width="260px">
            </el-table-column>
            <!-- <el-table-column
            prop="address"
            label="交易类型">
            </el-table-column> -->
            <el-table-column
            prop="coinName"
            label="货币名称" >
            </el-table-column>
            <el-table-column
            prop="gasFee"
            label="矿工费" >
            </el-table-column>
            <el-table-column
            label="创建时间" width="170px">
                 <template slot-scope="scope">
                    <span >{{ scope.row.createdAt | time }}</span>
                </template>
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
import { GasDetails,becameStatus,allPlatform } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
          orderTxHash:'',
         fromAddress:'',
         toAddress:'',
          date:'',
          date1:'',
         
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
            this.formInline.orderTxHash=''
            this.formInline.fromAddress=''
            this.formInline.date=''
            this.formInline.date1=''
            this.formInline.fromAddress=''
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
            if(this.formInline.orderTxHash!=''){
                 para=para+'&orderTxHash='+this.formInline.orderTxHash
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
            GasDetails(para).then(data=>{
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
