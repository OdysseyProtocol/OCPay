<template>
  <div>
    <h3>用户钱包  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="22">
            <el-form-item label="所属平台">
                 <el-select v-model="formInline.platform" placeholder="请选择平台类型" @change="onSubmit">
                   <el-option v-for="(item,index) in platform" :key="index" :label="item.merchantName" :value="item.id"></el-option>
                    
                </el-select>
            </el-form-item>
              <el-form-item class="address" label="钱包地址">
                <el-input v-model="formInline.address" placeholder="请输入钱包地址" @change="onSubmit"></el-input>
            </el-form-item>  
        </el-col>
        <el-col :span="2">
            <el-button type="primary" @click="onSubmit">查 询</el-button>
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
            <!-- <el-table-column
            prop="id"
            label="id">
            </el-table-column> -->
            <el-table-column
            width="150px"
            label="序号">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <el-table-column
            prop="userid"
            label="用户id">
            </el-table-column>
            <el-table-column
            label="第三方平台">
                <template slot-scope="scope">
                     <span v-for="(item,index) in platform" :key="index" v-if="item.id==scope.row.merchantId">{{item.merchantName}} </span>
                </template>
            </el-table-column>
            <el-table-column
            prop="coinAddress"
            width="550px"
            label="钱包地址">
            </el-table-column>
            <el-table-column
            label="用户注册时间">
                <template slot-scope="scope">
                    <span >{{ scope.row.createdAt | time }}</span>
                </template>
            </el-table-column>
            <!-- <el-table-column
            prop="createdBy"
            label="createdBy">
            </el-table-column> -->
            <!-- <el-table-column
            prop="coinBalance"
            label="用户账户余额">
            </el-table-column>
            <el-table-column
            label="最后交易时间">
                <template slot-scope="scope">
                    <span >{{ scope.row.lastTradingTime | time }}</span>
                </template>
            </el-table-column>
            <el-table-column
            prop="coinAddress"
            label="用户平台钱包地址">
            </el-table-column> -->
            <!-- <el-table-column
            prop="userStatus"
            label="用户状态">
            </el-table-column> -->
            <!-- <el-table-column
            prop="address"
            label="管理">
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
import { userList,allPlatform } from '../../api/api';
export default {
    data() {
        return {
            formInline: {
            platform:'',
            address:''
            },
            tableData: [],
            platform:[],
             //默认每页数据量
            pagesize: 10,
            //当前页码
            currentPage: 1,
            //默认数据总数
            totalCount: 20,
            

        };
    },
    mounted(){
    //    userList().then((data)=>{
    //         console.log(data)
    //     })
        this.getcoin(this.currentPage)
        
        this.tableData.lastTradingTime=new Date(this.tableData.lastTradingTime);
        allPlatform().then(data=>{
            // console.log(data.data);
            this.platform=data.data;
        })
    },
    methods: {
        onSubmitForm(){
            this.formInline.platform=''
            this.formInline.address=''
           
            this.getcoin(this.currentPage);
        },
        getcoin(num){
            let para='?pageNum='+num
            if(this.formInline.platform!=''){
                 para=para+'&merchantId='+this.formInline.platform
            }
            if(this.formInline.address!=''){
                 para=para+'&coinAddress='+this.formInline.address
            }
            userList(para).then(data=>{
                // console.log(data)
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
        onSubmit() {

           this.getcoin(this.currentPage);
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
