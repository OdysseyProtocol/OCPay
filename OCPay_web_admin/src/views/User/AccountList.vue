<template>
  <div>
    <h3>账户列表  </h3>
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
            prop="name"
            label="用户名">
            </el-table-column>
            <el-table-column
            prop="realName"
            label="真实姓名">
            </el-table-column>
            
            <el-table-column
            prop="phone"
            label="电话号码">
            </el-table-column>
           
            <!-- <el-table-column
            label="创建时间">
                 <template slot-scope="scope">
                    <span >{{ scope.row.createTime | time }}</span>
                </template>
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
import { AccountList } from '../../api/api';
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
        
        this.getcoin(this.currentPage);
    },
    methods: {
       
        getcoin(num){
           
            let para='?pageNum='+num
            
            AccountList(para).then(data=>{
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
