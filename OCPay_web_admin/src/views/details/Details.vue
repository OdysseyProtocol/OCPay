<template>
  <div>
    <h3>交易明细  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-col :span="22">
            <el-form-item label="所属平台">
                 <el-select v-model="formInline.platform" placeholder="请选择平台类型">
                    <el-option label="oBike" value="oBike"></el-option>
                    <el-option label="Odyssey" value="Odyssey"></el-option>
                    
                </el-select>
            </el-form-item>
             <el-form-item label="交易状态">
                 <el-select v-model="formInline.state" placeholder="请选择交易状态">
                     <el-option label="等待" value="等待"></el-option>
                    <el-option label="成功" value="成功"></el-option>
                    <el-option label="失败" value="失败"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="交易时间">
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="formInline.date1" style="width: 100%;"></el-date-picker>
                </el-col>
            </el-form-item>

            <el-form-item label="用户编号">
                <el-input v-model="formInline.number" placeholder="123"></el-input>
            </el-form-item>
            <el-form-item label="钱包地址">
                <el-input v-model="formInline.address" placeholder="15sbadk6466"></el-input>
            </el-form-item>
        </el-col>
        <el-col :span="2">
            <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-col>
        <div style="clear:both;"></div>
    </el-form>  
    <hr/>
    <div class="table">
        <el-table
            :data="tableData"
            border
            style="width: 100%">
            <el-table-column
            prop="coinId"
            label="交易编号">
            </el-table-column>
            <el-table-column
            prop="merchantName"
            label="所属平台">
            </el-table-column>
            <!-- <el-table-column
            prop="address"
            label="交易类型">
            </el-table-column> -->
            <el-table-column
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
import { Details,becameStatus } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
          platform:'',
          type: '',
          state: '',
          date:'',
          date1:'',
          number:'',
          address:''
        },
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
        onSubmit() {

            console.log(this.formInline);
        },
        getcoin(num){
            let para='?pageNum='+num
            // console.log(para)
            Details(para).then(data=>{
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
                console.log(data);
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
