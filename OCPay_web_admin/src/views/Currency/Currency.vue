<template>
  <div>
    <h3>货币列表  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        
        <el-button type="primary" @click="build">新增货币</el-button>
    </el-form>  
    <el-dialog
    title="新增货币"
    :visible.sync="buildThird"
    width="30%">
    <el-form ref="form" :model="buildm" label-width="150px">
        <el-form-item label="货币名称">
            <el-input v-model="buildm.fullName"></el-input>
        </el-form-item>
         <el-form-item label="货币简称">
            <el-input v-model="buildm.coinName"></el-input>
        </el-form-item>
        <el-form-item label="ERC20合约">
            <el-input v-model="buildm.contractAddress"></el-input>
        </el-form-item>
        
     </el-form>
    <span slot="footer" class="dialog-footer">
        <el-button @click="buildThird = false">取 消</el-button>
        <el-button type="primary" @click="submit1()">提交</el-button>
    </span>
    </el-dialog>
    <hr/>
    <div class="table">
        <el-table
            :data="tableData"
            class="table1"
            border
            max-height="550"
            style="width: 100%">
            <el-table-column
            label="序号">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <el-table-column
            prop="id"
            label="货币id">
            </el-table-column>
            <el-table-column
            prop="fullName"
            label="货币名称">
            </el-table-column>
            <el-table-column
            prop="coinName"
            label="货币简称">
            </el-table-column>
            <!-- <el-table-column
            label="状态">
                <template slot-scope="props">
                    <span v-if="props.row.status==0">开启</span>
                    <span v-if="props.row.status==1">关闭</span>
					
				</template>
            </el-table-column> -->
            <el-table-column
            prop="contractAddress"
            label="ERC20合约地址">
            </el-table-column>
            <el-table-column
            prop="coinHigherLimit"
            label="个人平台钱包征收阈值">
            </el-table-column>
             <el-table-column
            prop="coinLowerLimit"
            label="个人平台钱包周期征收阈值">
            </el-table-column>
             <el-table-column
            prop="dayCycleRound"
            label="周期征收时限/天">
            </el-table-column>
            <el-table-column label="管理" >
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <!-- <el-button size="small" @click="look(scope.$index, scope.row)">编辑汇总阈值</el-button> -->
				</template>
			</el-table-column>
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
   
   
    <!-- 编辑货币 -->
    <el-dialog
    title="编辑货币"
    :visible.sync="dialogEdit"
    width="30%"
    class="bianji">
    
        <el-form ref="form" :model="formEdit" label-width="180px">
        <el-form-item label="货币id">
            <!-- <el-input v-model="formEdit.id"></el-input> -->
            <span>{{formEdit.id}}</span>    
        </el-form-item>
        <el-form-item label="名称">
            <el-input v-model="formEdit.fullName"></el-input>
        </el-form-item>
        <el-form-item label="货币简称">
            <el-input v-model="formEdit.coinName"></el-input>
        </el-form-item>
        
        <el-form-item label="ERC20合约">
            <el-input v-model="formEdit.contractAddress" ></el-input>
        </el-form-item>
        
        <el-form-item label="个人平台钱包征收阈值">
            <el-input v-model.number="formEdit.coinHigherLimit" ></el-input>
        </el-form-item>
        <el-form-item label="个人平台钱包周期征收阈值">
            <el-input v-model.number="formEdit.coinLowerLimit" ></el-input>
        </el-form-item>
        <el-form-item label="周期征收时限/天">
            <el-input v-model.number="formEdit.dayCycleRound" ></el-input>
        </el-form-item>
        
        
        </el-form>

        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogEdit = false">取 消</el-button>
            <el-button type="primary" @click="became">提交</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import { getCurrency,addCurrency,becameCurrency } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
          platform: '',
          id: '',
          address: '',
          type: ''
        },
        buildm:{
            fullName:'',
            coinName:'',
            contractAddress:''
        },
        tableData: [],
        dialogVisible:false,
        dialogEdit:false,
        erweima:false,
        buildThird:false,
        form: {
          num:'122',
          number: '22',
          date: '22',
          
        },
        formEdit:{

        },
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
        // 获取货币信息
        getcoin(pageNum){
            getCurrency(pageNum).then(data=>{
                this.tableData=data.data.list;
                this.pagesize=data.data.per;
                this.totalCount=data.data.per*data.data.pages;
            //    console.log(data.data.list) 
            })
        },
        build(){
            this.buildThird=true;
        },
        // 新增货币
        submit1(){
            
            console.log(this.buildm)
            this.$confirm('确认增加此货币？')
            .then(_ => {
                this.buildThird = false
                addCurrency(this.buildm).then(data=>{
                    console.log(data)
                    
                   this.getcoin(this.currentPage);
                   this.$message({
                    message: data.data.message,
                    type: 'success'
                    });
                })
            }).catch(_ => { 
                console.log("取消")
            });
            
        },
        // 编辑货币
        handleEdit(index,row){
            this.dialogEdit=true;
            console.log(row);
            this.formEdit=row;
            
        },
        //提交编辑
        became(){
            if(this.formEdit.dayCycleRound<=31){
                this.$confirm('确认修改此货币信息？')
                .then(_ => {
                    becameCurrency(this.formEdit).then(data=>{
                        // console.log(this.formEdit)
                        // console.log(data)
                        this.dialogEdit=false;
                    this.getcoin(this.currentPage);
                    this.$message({
                        message: data.data.message,
                        type: 'success'
                        });
                    })
                }).catch(_ => { 
                    console.log("取消")
                });
            }else{
                this.$message.error('周期征收时限最多为31天');
            }
            
        },
        handleCurrentChange(val){
            // console.log(val)
            this.currentPage=val;
            this.getcoin(this.currentPage);
        }
       
    }
}

</script>
<style>
.bianji .el-dialog--small{
    width: 40%;
}
.table1 .cell{
    text-align: center;
}
.table1 .cell button{
    margin: 5px;
}
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
/* .el-dialog--small{
    width: 30%;
} */
</style>
