<template>
  <div>
    <h3>第三方列表  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        
        <el-button type="primary" @click="look">新建第三方</el-button>
    </el-form>  
    <hr/>
    <div class="table third">
        <el-table
            :data="tableData"
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
            label="第三方编号">
            </el-table-column>
            <el-table-column
            prop="merchantName"
            label="名称">
            </el-table-column>
            <!-- <el-table-column
            prop="address"
            label="货币类型">
            </el-table-column> -->
            <el-table-column
            prop="apikey"
            label="apikey">
            </el-table-column>
            <el-table-column
            prop="security"
            label="security">
            </el-table-column>
            <el-table-column
            prop="rechargeSuccessUrl"
            label="支付回调地址">
            </el-table-column>
            <el-table-column
            prop="transferCallBackUrl"
            label="转账回调地址">
            </el-table-column>
            <el-table-column label="第三方操作操作" >
				<template scope="scope">
                    <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">编辑第三方</el-button>
				</template>
			</el-table-column>
            <el-table-column label="钱包组操作" >
				<template scope="scope">
                     <el-button type="success" size="small" @click="addEdit1(scope.$index, scope.row)">添加</el-button>
                      <el-button type="warning" size="small" @click="delEdit1(scope.$index, scope.row)">查看</el-button>
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
    <!-- 钱包组详情 -->
    <!-- <el-dialog title="钱包组详情" :visible.sync="dialogVisible"> -->
    <!-- <el-dialog
    title="钱包组详情"
    :visible.sync="dialogVisible"
    width="30%"> -->
         <!-- <el-dialog
            width="30%"
            title="内层 Dialog"
            :visible.sync="erweima"
            append-to-body>
            </el-dialog> -->

    <el-dialog title="新建第三方" 
        :visible.sync="dialogVisible" 
        width="20%" 
        center
        class="xinjian1">
        
        <el-form ref="form" :model="form" label-width="150px">
        <el-form-item label="第三方名称">
            
           <el-col :span="15"> <el-input v-model="form.merchantName" ></el-input></el-col>
        </el-form-item>
        <el-form-item label="支付回调地址">
            <el-col :span="15"><el-input v-model="form.rechargeSuccessUrl" ></el-input></el-col>
        </el-form-item>
        <el-form-item label="转账回调地址">
            <el-col :span="15"><el-input v-model="form.transferCallBackUrl" ></el-input></el-col>
        </el-form-item>
        <el-form-item label="apikey">
            <el-col :span="15"><el-input v-model="form.apikey" disabled=""></el-input></el-col>
            <!-- <el-col :span="2"> </el-col> -->
            <el-col :span="5" class="buttonCenter"><el-button type="success" @click="apiKey">生成</el-button></el-col>
        </el-form-item>
        <el-form-item label="security">
            <el-col :span="15"><el-input v-model="form.security" disabled=""></el-input></el-col>
            <!-- <el-col :span="2"> </el-col> -->
            <el-col :span="5" class="buttonCenter"><el-button type="success" @click="security">生成</el-button></el-col>
        </el-form-item>
        </el-form>
           
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="buildThird()">创建</el-button>
        </span>
    </el-dialog>
    <!-- 编辑钱包组 -->
    <el-dialog
    title="编辑第三方"
    :visible.sync="dialogEdit"
    width="30%"
    class="xinjian1">
    
        <el-form ref="form" :model="formEdit" label-width="150px">
        <el-form-item label="第三方id:">
            <el-col :span="12">{{formEdit.id}}</el-col>
            
        </el-form-item>
        <el-form-item label="第三方名称:">
            <el-col :span="15"><el-input v-model="formEdit.merchantName"></el-input></el-col>
            <!-- <el-col :span="12">{{formEdit.merchantName}}</el-col> -->
        </el-form-item>
        
        <el-form-item label="支付回调地址">
            <el-col :span="15"><el-input v-model="formEdit.rechargeSuccessUrl"></el-input></el-col>
        </el-form-item>
        <el-form-item label="转账回调地址">
            <el-col :span="15"><el-input v-model="formEdit.transferCallBackUrl"></el-input></el-col>
        </el-form-item>
        <el-form-item label="apikey">
            <el-col :span="15"><el-input v-model="formEdit.apikey" disabled=""></el-input></el-col>
            <!-- <el-col :span="2"> </el-col> -->
            <el-col :span="5" class="buttonCenter"><el-button type="success" @click="apiKeyReset">重置</el-button></el-col>
        </el-form-item>
        <el-form-item label="security">
            <el-col :span="15"><el-input v-model="formEdit.security" disabled="" ></el-input></el-col>
            <!-- <el-col :span="2"> </el-col> -->
            <el-col :span="5" class="buttonCenter"><el-button type="success" @click="securityReset">重置</el-button></el-col>
        </el-form-item>
        </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button @click="dialogEdit = false">取 消</el-button>
        <el-button type="primary" @click="becameThirdWallet()">提交</el-button>
    </span>
    </el-dialog>

     <el-dialog
    title="添加钱包组"
    :visible.sync="addEdit"
    width="30%"
    class="peizhi">
    
        <el-form ref="form" :model="formEdit" label-width="150px">
        <el-form-item label="第三方id:">
            <el-col :span="12">{{formEdit.id}}</el-col>
            
        </el-form-item>
        <el-form-item label="第三方名称:">
            <!-- <el-input v-model="formEdit.merchantName"></el-input> -->
            <el-col :span="12">{{formEdit.merchantName}}</el-col>
        </el-form-item>
        <el-form-item label="选择钱包组类型">
            <!-- <el-input v-model="formEdit.thirdId"></el-input> -->
            <el-select v-model="formEdit.type" placeholder="请选择钱包组类型" @change="change">
                    <el-option label="邮费钱包" value="1"></el-option>
                    <el-option label="充值钱包" value="2"></el-option>
                    <el-option label="提现钱包" value="3" disabled></el-option>
                    
            </el-select>
        </el-form-item>
        <el-form-item label="第三方钱包组名称">
            <!-- <el-input v-model="formEdit.thirdId"></el-input> -->
            <el-select v-model.number="formEdit.groupId" placeholder="请选择钱包组">
                    <el-option v-for="(item,index) in wallName" :key='index' :label='item.groupName' :value="item.id"></el-option>
                    <!-- <el-option label="Odyssey" value="Odyssey"></el-option> -->
                    
            </el-select>
        </el-form-item>
       
        
        </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button @click="addEdit = false">取 消</el-button>
        <el-button type="primary" @click="addThirdWallet()">提交</el-button>
    </span>
    </el-dialog>
    
     <el-dialog
    title="删除钱包组"
    :visible.sync="delEdit"
    width="30%"
    class="peizhi delwallet">
    
        <el-form ref="form" :model="formEdit" label-width="150px">
        <el-form-item label="第三方id:">
            <el-col :span="12">{{formEdit.id}}</el-col>
            
        </el-form-item>
        <el-form-item label="第三方名称:">
            <!-- <el-input v-model="formEdit.merchantName"></el-input> -->
            <el-col :span="12">{{formEdit.merchantName}}</el-col>
        </el-form-item>
         <el-form-item label="第三方钱包组名称" v-for="(item,index) in agoName" :key='index'>
            <el-col :span="18">
                <el-input v-model="item.groupName" disabled=""></el-input>
            </el-col>
           
            <el-col :span="6" class="add1" >
                
				  <el-button type="danger" size="small" @click="del(item.id)">删除</el-button>
            </el-col>
        </el-form-item>
        <!-- <el-form-item label="第三方钱包组名称">
            <el-select v-model.number="formEdit.groupId" placeholder="请选择钱包组">
                    <el-option v-for="(item,index) in wallName" :key='index' :label='item.groupName' :value="item.id"></el-option>
                    <el-option label="Odyssey" value="Odyssey"></el-option>
                    
            </el-select>
        </el-form-item> -->
        
        
        </el-form>

    <!-- <span slot="footer" class="dialog-footer">
        <el-button @click="dialogEdit = false">取 消</el-button>
        <el-button type="primary" @click="delThirdWallet()">删除</el-button>
    </span> -->
    </el-dialog>
  </div>
</template>

<script>
import { getThird,addThird,becameThird,getThirdWallet,delThirdWallet,addThirdWallet,getThirdWalletAgo,apiKey,security } from '../../api/api';
export default {
    data() {
    return {
        formInline: {
          user: '',
          region: ''
        },
        tableData: [],
        dialogVisible:false,
        dialogEdit:false,
        erweima:false,
        addEdit:false,
        delEdit:false,
        form: {
          merchantName: '',
           apikey:'',
           security:'',
           rechargeSuccessUrl:'',
           transferCallBackUrl:''
        },
        wallName:[],
        agoName:[],
        formEdit:{
            wallName:{},
            groupId:1,
            type:'',
            apikey:'',
            security:''
        },
         //默认每页数据量
        pagesize: 10,
        //当前页码
        currentPage: 1,
        //默认数据总数
        totalCount: 20,

    };
    },
    created(){
        //  getThirdWallet().then(data=>{
        //      this.wallName=data.data;
        //     //  console.log(this.wallName)
        //  })
    },
    mounted(){
         this.getcoin(this.currentPage)
        
    },
    methods: {
        getcoin(num){
            getThird(num).then(data=>{
                // console.log(data)
                this.tableData=data.data.list;
                this.pagesize=data.data.per;
                this.totalCount=data.data.per*data.data.pages;
            })
        },
        onSubmit() {
            console.log('submit!');
        },
        look(){
            this.dialogVisible=true;
        },
        buildThird(){
            addThird(this.form).then(data=>{
                // console.log(this.form)
                 this.dialogVisible=false;
                 this.$message({
                    message: data.data,
                    type: 'success'
                });
                this.getcoin(this.currentPage)
                //  console.log(data)
            })
           

        },
        apiKey(){
            apiKey().then(data=>{
                this.form.apikey=data.data;
                console.log(data.data)
            })
        },
         security(){
            apiKey().then(data=>{
                this.form.security=data.data;
                console.log(data.data)
            })
        },
        apiKeyReset(){
            apiKey().then(data=>{
                this.formEdit.apikey=data.data;
                console.log(data.data)
            })
        },
         securityReset(){
            apiKey().then(data=>{
                this.formEdit.security=data.data;
                console.log(data.data)
            })
        },
        becameThirdWallet(){
            // this.formEdit.wallName=this.wallName[ this.formEdit.index]
            becameThird(this.formEdit).then(data=>{
                // console.log(this.formEdit)
                this.getcoin(this.currentPage)
                this.dialogEdit=false;
                this.$message({
                    message: data.data,
                    type: 'success'
                });
            })
        },
        addThirdWallet(){
            let para={
                merchantId:this.formEdit.id,
                groupId:this.formEdit.groupId
            }
            
            addThirdWallet(para).then(data=>{
                this.$message({
                    message: data.data,
                    type: 'success'
                });
                this.formEdit.type=''
                this.wallName.length=0;
                this.formEdit.groupId=''
                this.addEdit=false;
                //  console.log(data);
                // console.log(para);
            })
        },
        // delThirdWallet(){
        //     let para={
        //         id:this.formEdit.id,
        //         groupId:this.formEdit.groupId
        //     }

        //     this.$confirm('确认删除此钱包组？')
        //     .then(_ => {
        //         delThirdWallet(para).then(data=>{
        //          console.log(data);
        //         console.log(para);
        //     })
        //     })
        //     .catch(_ => { console.log("取消")});
        // },
        handleEdit(index,row){
            this.dialogEdit=true;
            // console.log(index,row)
            this.formEdit=row;
        },
        addEdit1(index,row){
            this.addEdit=true;
            this.formEdit=row;
        },
        delEdit1(index,row){
            this.delEdit=true;
            this.formEdit=row;
            let para='?id='+this.formEdit.id
            getThirdWalletAgo(para).then(data=>{
                this.agoName=data.data
                // console.log(para)
                // console.log(data.data)
            })
        },
        del(index){
            let para={
                id:this.formEdit.id,
                groupId:index,
                _method:'delete'
            }
             this.$confirm('确认删除？')
            .then(_ => {
                delThirdWallet(para).then(data=>{

                    let para1='?id='+this.formEdit.id
                    getThirdWalletAgo(para1).then(data1=>{
                        this.agoName=data1.data
                        
                    })
                    
                })
            })
            // console.log(para)
        },
        // handleDel(){

        // },
        // add(index){
        // },
        // edit(index){
        //     console.log(index)
        // },
        // del(index){

        // },
        // lookEr(){
        //     this.erweima=true;
        // },
        handleCurrentChange(val){
            // console.log(val)
            this.currentPage=val;
            this.getcoin(this.currentPage);
        },
        change(){
            // console.log(this.formEdit.type)
            let para='?merchantId='+this.formEdit.id+'&groupType='+this.formEdit.type
            getThirdWallet(para).then(data=>{
                // console.log(data)
                // console.log(para)
                this.wallName=data.data;
                //  console.log(this.wallName)
            })
        }
    }
}

</script>
<style>
.buttonCenter{
    text-align: center;
}
.xinjian .el-dialog--small{
    width: 30%;
}
.peizhi .el-dialog--small{
    width: 30%;
}
@media screen and (max-width: 1700px) {
    .bigWidth .el-dialog--small{
        width: 70%;
    }
}

.xinjian1 .el-dialog--small{
    width: 40%;
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
.third .cell{
    text-align: center;
}
.third .cell button{
    margin: 5px;    
}
.delwallet .el-input.is-disabled .el-input__inner{
    color: #666;
}
input{
    border-radius: 0!important;
}
</style>
