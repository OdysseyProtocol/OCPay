<template>
  <div>
    <h3>错误订单  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="22">
            <!-- <el-form-item label="错误id">
                <el-input v-model="formInline.id" placeholder="请输入错误id"  @change="onSubmit"></el-input>
            </el-form-item> -->
             <el-form-item class="address" label="订单号">
                <el-input v-model="formInline.txHash" placeholder="请输入订单号"  @change="onSubmit"></el-input>
            </el-form-item>
            <el-form-item label="交易时间">
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.beginTime" style="width: 100%;"  @change="onSubmit"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.endTime" style="width: 100%;"  @change="onSubmit"></el-date-picker>
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
            max-height="550"
            style="width: 100%">
            <el-table-column
            width="150px"
            label="序号">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <el-table-column
            prop="id"
            width="220px"
            label="错误id">
            </el-table-column>
            <el-table-column
            prop="txHash"
            label="订单号">
            </el-table-column>
            
           
           
            <el-table-column
            label="创建时间">
                 <template slot-scope="scope">
                    <span >{{ scope.row.createTime | time }}</span>
                </template>
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
import { ErrorDetails,becameStatus,allPlatform } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
         beginTime:'',
         endTime:'',
         id:'',
         txHash:''
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
            this.formInline.txHash=''
            this.formInline.id=''
            this.formInline.beginTime=''
            this.formInline.endTime=''
            this.getcoin(this.currentPage);
        },
        onSubmit() {
           
            this.getcoin(this.currentPage);
        },
        getcoin(num){
            let beginTime=new Date(this.formInline.beginTime).getTime();
            let endTime=new Date(this.formInline.endTime).getTime()+60*60*24*1000;
            // console.log(date,date1)
            let para='?pageNum='+num
            // if(this.formInline.id!=''){
            //      para=para+'&id='+this.formInline.id
            // }
            if(this.formInline.txHash!=''){
                 para=para+'&txHash='+this.formInline.txHash
            }
            if(this.formInline.beginTime!=''){
                 para=para+'&beginTime='+beginTime
            }
            if(this.formInline.endTime!=''){
                 para=para+'&endTime='+endTime
            }
           
            // console.log(para)
            ErrorDetails(para).then(data=>{
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
