<template>
  <div>
    <h3>对账列表  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="21">
            <el-form-item label="所属平台">
                 <!-- <el-input v-model="formInline.platform" placeholder="填写平台名字"></el-input> -->
                 <el-select v-model="formInline.platform" placeholder="请选择平台类型"  @change="onSubmit">
                     <el-option label="所有" value=""></el-option>
                    <el-option v-for="(item,index) in platform" :key="index" :label="item.merchantName" :value="item.id"></el-option>
                    <!-- <el-option label="Odyssey" value="Odyssey"></el-option> -->
                    
                </el-select>
            </el-form-item>
            
            <el-form-item label="交易时间">
                <el-col :span="11">
                <el-date-picker type="datetime" placeholder="选择日期时间" v-model="formInline.date"  @change="onSubmit" style="width: 100%;"  default-time="['18:00:00']"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                <el-date-picker type="datetime" placeholder="选择日期" v-model="formInline.date1"  @change="onSubmit" style="width: 100%;" default-time="['18:00:00']"></el-date-picker>
                </el-col>
            </el-form-item>

            
        </el-col>
        <el-col :span="3">
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
            prop="merchantName"
            label="平台名称" >
            </el-table-column>
             <el-table-column
            prop="rechargeAmount"
            label="充值总额 " >
            </el-table-column>
             <el-table-column
            prop="rechargeNum"
            label="充值次数 " >
            </el-table-column>
             <el-table-column
            prop="consumeAmount"
            label="消费总额" >
            </el-table-column>
             <el-table-column
            prop="consumeNum"
            label="消费次数 " >
            </el-table-column>
             
             <!-- <el-table-column
            prop="merchantId"
            label="第三方商户id">
            
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
import { Account,becameStatus,allPlatform } from '../../api/api';
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
            let date1=new Date(this.formInline.date1).getTime();
            // console.log(date,date1)
            let para='?pageNum='+num
            if(this.formInline.platform!=''){
                 para=para+'&merchantId='+this.formInline.platform
            }
           
            if(this.formInline.date!=''){
                 para=para+'&beginTradingTime='+date
            }
            if(this.formInline.date1!=''){
                 para=para+'&endTradingTime='+date1
            }
            
            // console.log(para)
            Account(para).then(data=>{
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
