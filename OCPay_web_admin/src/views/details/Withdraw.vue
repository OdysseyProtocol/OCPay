<template>
  <div>
    <h3>钱包组列表  </h3>
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
            prop="date"
            label="交易编号">
            </el-table-column>
            <el-table-column
            prop="name"
            label="所属平台">
            </el-table-column>
            <el-table-column
            prop="address"
            label="交易类型">
            </el-table-column>
            <el-table-column
            prop="address"
            label="用户编号">
            </el-table-column>
            <el-table-column
            prop="address"
            label="发款钱包">
            </el-table-column>
            <el-table-column
            prop="address"
            label="收款钱包">
            </el-table-column>
            <el-table-column
            prop="address"
            label="交易状态">
            </el-table-column>
            <el-table-column
            prop="address"
            label="交易时间">
            </el-table-column>
            <el-table-column
            prop="address"
            label="GAS费用">
            </el-table-column>
            <el-table-column
            prop="address"
            label="交易地址">
            </el-table-column>
        </el-table>
    </div>
    
    
  </div>
</template>

<script>
import { addWallet } from '../../api/api';
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
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '2'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '3'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '4'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '5'
        }]
        
        

    };
    },
    mounted(){
        
    },
    methods: {
        onSubmit() {

            console.log(this.formInline);
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
