<template>
  <div>
    <h3>钱包组列表  </h3>
    <hr/>
    <el-form  :inline="true" :model="formInline" class="demo-form-inline wallet">
        <el-form-item label="所属平台">
             <el-select v-model="formInline.platform" placeholder="请选择平台类型" @change="onSubmit">
                  <el-option label="所有" value=""></el-option>
                    <el-option v-for="(item,index) in platform" :key="index" :label="item.merchantName" :value="item.id"></el-option>
                    <!-- <el-option label="Odyssey" value="Odyssey"></el-option> -->
                    
                </el-select>
        </el-form-item>
        <el-form-item label="钱包组类型">
            <el-select v-model="formInline.type" placeholder="钱包组类型" @change="onSubmit">
            <el-option label="所有" value=""></el-option>
            <el-option label="邮费钱包" value="1"></el-option>
            <el-option label="充值钱包" value="2"></el-option>
            <el-option label="提现钱包" value="3" disabled></el-option>
            <!-- <el-option label="糖果钱包" value="糖果钱包"></el-option>
              <el-option label="度支钱包" value="度支钱包"></el-option> -->
            </el-select>
        </el-form-item>
        <el-form-item label="钱包组id">
            <el-input v-model="formInline.id" placeholder="请输入钱包组id" @change="onSubmit"></el-input>
        </el-form-item>
        <el-form-item label="钱包地址" class="address">
            <el-input v-model="formInline.address" placeholder="请输入钱包地址" @change="onSubmit"></el-input>
        </el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="primary" @click="onSubmitForm">清空</el-button>
        <el-button type="primary" @click="build">新建钱包组</el-button>
    </el-form>  
    <el-dialog
    title="新建钱包组"
    :visible.sync="buildThird"
    width="30%"
    class="xinjian">
    <el-form ref="form" :model="buildm" label-width="150px">
    <el-form-item label="钱包组名称">
            <el-input v-model="buildm.groupName"></el-input>
        </el-form-item>
        <el-form-item label="钱包组类型">
            <el-select v-model="buildm.groupType" placeholder="钱包组类型">
                <el-option label="邮费钱包组" value="1"></el-option>
                <el-option label="充值钱包组" value="2"></el-option>
                <el-option label="提现钱包组" value="3"></el-option>
            </el-select>
        </el-form-item>
     </el-form>
    <span slot="footer" class="dialog-footer">
        <el-button @click="buildThird = false">取 消</el-button>
        <el-button type="primary" @click="buildwallet">确 定</el-button>
    </span>
    </el-dialog>
    <hr/>
    <div class="table">
        <el-table
            :data="tableData"
            border
            max-height="500"
            style="width: 100%">
            <!-- <el-table-column
            prop="id"
            label="钱包组id">
            </el-table-column> -->
             <el-table-column
             width="150px"
            label="序号">
                <template slot-scope="scope1">
                    
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
            </el-table-column>
            <el-table-column
            prop="groupName"
            label="钱包组名称">
            </el-table-column>
            <el-table-column
            prop="address"
            label="钱包组类型">
                <template slot-scope="props">
                    <span v-if="props.row.groupType==1">邮费钱包组</span>
                    <span v-if="props.row.groupType==2">充值钱包组</span>
                    <span v-if="props.row.groupType==3">提现钱包组</span>
					
				</template>
            </el-table-column>
            <!-- <el-table-column
            prop="address"
            label="钱包组货币">
            </el-table-column> -->
            <el-table-column
            prop="platforms"
            label="钱包组所属平台">
            </el-table-column>
            <el-table-column
            prop="walletCount"
            label="钱包地址数">
            </el-table-column>
            <el-table-column
            prop="balanceCount"
            label="钱包总额">
            </el-table-column>
            <el-table-column label="管理" >
				<template slot-scope="scope">
                    <el-button size="small" @click="look(scope.$index, scope.row)">详情</el-button>
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
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
        <el-dialog
            width="30%"
            class="small"
            title="二维码"
            :visible.sync="erweima"
            append-to-body="true"
            :before-close="handleClose">
            <!-- <img src="../../assets/erweima.png" alt=""> -->
            <div id="qrcodeNode"></div>
            <span>钱包地址:<br/>{{ eaddress }}</span>
      </el-dialog>
    <el-dialog title="钱包组详情" :visible.sync="dialogVisible" class="bigWidth">
       
        <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="钱包组id:">
            {{form.id}}
        </el-form-item>
        <el-form-item label="钱包组名称:">
            {{form.groupName}}
        </el-form-item>
        <el-form-item label="钱包组类型:">
            <span v-if="form.groupType==1">邮费钱包组</span>
            <span v-if="form.groupType==2">充值钱包组</span>
            <span v-if="form.groupType==3">提现钱包组</span>
        </el-form-item>
        <!-- <el-form-item label="钱包组货币:">
            <el-input v-model="form.currency" disabled=""></el-input>
        </el-form-item> -->
        <el-form-item label="所属平台:">
            {{form.platforms}}
        </el-form-item>
        <el-form-item label="货币总额:">
            {{form.balanceCount}}
        </el-form-item>
        <el-form-item label="钱包地址" v-for="(item,index) in form.list" :key='index'>
            <el-col :span="13">
                <el-input v-model="item.walletAddress" disabled=""></el-input>
            </el-col>
            <el-col class="line" :span="2">余额：</el-col>
            <el-col :span="4">
                <span>{{item.balance}}</span>
             <!-- <el-input v-model="item.balance" disabled=""></el-input> -->
            </el-col>
             <!-- <el-col class="line" :span="3" v-if="form.groupType==1">交易次数：</el-col>
            <el-col :span="2" v-if="form.groupType==1">
                <span>{{item.nonce}}</span>
            </el-col> -->
            <el-col :span="2" class="erweima">
                 <el-button type="primary" @click="lookEr(item.walletAddress)">查看二维码</el-button>
            </el-col>
        </el-form-item>
        
        
        </el-form>
           

    </el-dialog>
    <!-- 编辑钱包组 -->
    <el-dialog
    title="编辑钱包组"
    :visible.sync="dialogEdit"
    width="30%" class="bigWidth">
    
        <el-form ref="form" :model="formEdit" label-width="150px">
        <el-form-item label="钱包组id">
            <!-- <el-input v-model="formEdit.id"></el-input> -->
            <span>{{formEdit.id}}</span>
        </el-form-item>
        <el-form-item label="钱包组名称">
            <el-input v-model="formEdit.groupName"></el-input>
        </el-form-item>
        <el-form-item label="钱包组类型">
            <span v-if="formEdit.groupType==1">邮费钱包组</span>
            <span v-if="formEdit.groupType==2">充值钱包组</span>
            <span v-if="formEdit.groupType==3">提现钱包组</span>
        </el-form-item>
        <!-- <el-form-item label="钱包组货币">
            <el-input v-model="formEdit.currency"></el-input>
        </el-form-item> -->
        <el-form-item label="所属平台">
            <!-- <el-button type="primary" size="small" v-for='(item,index) in formEdit.platform' :key='index'>{{item}}</el-button> -->
             {{formEdit.platforms}}
        </el-form-item>
        <el-form-item label="货币总额">
            <!-- <el-input v-model="formEdit.balanceCount" ></el-input> -->
            <span>{{formEdit.balanceCount}}</span>
        </el-form-item>
        <el-form-item label="钱包地址" v-for="(item,index) in formEdit.list" :key='index'>
            <el-col :span="12">
                <!-- <el-input v-model="item.walletAddress" disabled=""></el-input> -->
                <span>{{item.walletAddress}}</span>
            </el-col>
            <!-- <el-col class="secret" :span="2" v-if="formEdit.groupType=='1'">
                私钥
            </el-col>
            <el-col :span="8" v-if="formEdit.groupType=='1'">
                <el-input v-model="item.privateKey" disabled=""></el-input>
            </el-col> -->
            <el-col :span="4" class="add1" >
                
				  <el-button type="danger" size="small" @click="del(index)">删除</el-button>
                  <!-- <el-button size="small" @click="add(index)" v-if='index==formEdit.len-1'>新增</el-button> -->
            </el-col>
        </el-form-item>
        <el-form-item label="钱包地址" >
            <el-col :span="10">
                <el-input v-model="addadd.walletAddress" ></el-input>
            </el-col>
            <el-col class="secret" :span="2" v-if="formEdit.groupType=='1'">
                私钥
            </el-col>
            <el-col :span="8" v-if="formEdit.groupType=='1'">
                <el-input v-model="addadd.privateKey" ></el-input>
            </el-col>
            <el-col :span="4" class="add1" >
                
                  <el-button size="small" @click="add()" >新增</el-button>
            </el-col>
        </el-form-item>
         <!-- <el-col :span="8">
                <el-input v-model="addadd.name" ></el-input>
            </el-col>
            <el-col class="secret" :span="2" v-if="formEdit.type=='gas'">
                私钥
            </el-col>
            <el-col :span="8" v-if="formEdit.type=='gas'">
                <el-input v-model="addadd.name" ></el-input>
            </el-col>
            <el-col :span="6" class="add1" >
                  <el-button size="small" @click="add(index)" v-if='index==formEdit.len-1'>新增</el-button>
            </el-col> -->
        
        </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button @click="dialogEdit = false">取 消</el-button>
        <el-button type="primary" @click="submit1()">提交</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>
import { getWallet,addWallet,becameWallet,delWallet,allPlatform,addWalletAddress,delWalletAddress } from '../../api/api';

export default {
    data() {
    return {
        addadd:{
            name:'',
            walletAddress:'',
            privateKey:'',
            groupId:''

        },
        eaddress:'',
        formInline: {
          platform: '',
          id: '',
          address: '',
          type: ''
        },
        platform:[],
        buildm:{
            groupName:'',
            groupType:''
        },
        tableData: [],
        dialogVisible:false,
        dialogEdit:false,
        erweima:false,
        buildThird:false,
        form: {
         
        },
        formEdit:{
          
        },
        //默认每页数据量
        pagesize: 10,
        //当前页码
        currentPage: 1,
        //默认数据总数
        totalCount: 20,
        index1:''

    };
    },
    mounted(){
        // console.log(this.formEdit.len)
        this.getcoin(this.currentPage)
         allPlatform().then(data=>{
            // console.log(data.data);
            this.platform=data.data;
        })
    },
    methods: {
        onSubmitForm(){
            this.formInline.platform=''
            this.formInline.id=''
            this.formInline.address=''
            this.formInline.type=''
            this.getcoin(this.currentPage);
        },
        getcoin(num){
            let para='?pageNum='+num
            if(this.formInline.platform!=''){
                 para=para+'&merchantId='+this.formInline.platform
            }
            if(this.formInline.type!=''){
                 para=para+'&groupType='+this.formInline.type
            }
            if(this.formInline.id!=''){
                 para=para+'&id='+this.formInline.id
            }
            if(this.formInline.address!=''){
                 para=para+'&coinAddress='+this.formInline.address
            }
            getWallet(para).then(data=>{
                // console.log(data.data.list)
                this.tableData=data.data.list;
                this.pagesize=data.data.per;
                this.totalCount=data.data.per*data.data.pages;
            })
        },
        onSubmit() {
            // console.log('submit!');
            this.getcoin(this.currentPage)
        },
        look(index,row){
            this.dialogVisible=true;
            this.form=row;
        //    this.$store.state.count=row;
        },
        build(){
            this.buildThird=true;
            
        },
        buildwallet(){
            this.$confirm('确认添加钱包组？')
            .then(_ => {
                addWallet(this.buildm).then(data=>{
                    this.buildThird=false;
                    this.getcoin(this.currentPage);
                    this.buildm.groupName=''
                    this.buildm.groupType=''
                
                })
             })
            .catch(_ => { 
                console.log("取消")
            });
        },
        handleEdit(index,row){
            this.dialogEdit=true;
            this.formEdit=row;
            console.log(this.formEdit)
            this.addadd.groupId=this.formEdit.id;
             this.index1=index;
            // console.log(this.index1)
            // console.log(this.addadd.id);
        },
        handleDel(index,row){
            let para={
                "groupId":row.id
            }
            console.log(para)
            this.$confirm('确认删除？')
            .then(_ => {
                delWallet(para).then(data=>{
                    console.log(data)
                    this.getcoin(this.currentPage)
                     this.$message({
                        message: data.data.message,
                        type: 'success'
                    });
                })
            })
            .catch(_ => { 
                console.log("取消")
            });
        },
        add(index){
            // console.log(this.addadd.walletAddress)
            //  console.log(this.addadd.walletAddress.length)
           if(this.addadd.walletAddress.length==42){
               if(this.formEdit.list.length<12){
                let para={
                    groupId:this.addadd.groupId,
                    walletAddress:this.addadd.walletAddress,
                    privateKey:this.addadd.privateKey,
                    groupType:this.formEdit.groupType
                }
                addWalletAddress(para).then(data=>{
                    console.log(para)
                     this.getcoin(this.currentPage)
                     this.$message({
                        message: data.data.message,
                        type: 'success'
                        });
                        if(data.data.message=='成功'){
                            this.formEdit.list.push(para);
                        }
                    //     console.log(this.index1)
                    //   this.getcoin(this.currentPage)
                    //   setTimeout(() => {
                    //        this.formEdit=this.tableData[this.index1]
                    //         console.log( this.formEdit)
                    //   });
                      
                    //    console.
                        this.addadd.walletAddress=''
                        this.addadd.privateKey=''
                })
               
                // this.formEdit.len=this.formEdit.address.length;
                }else{
                    
                    this.$message.error('最多只能添加12个钱包');
                }
           }else{
                this.$message.error('钱包地址必须是42位');
           }
            
            
        },
        edit(index){
            console.log(index)
        },
        del(index){
            this.$confirm('确认删除？')
            .then(_ => {
                // console.log("删除")
                console.log(this.formEdit.list[index].walletAddress)
                let para={
                    "groupId":this.formEdit.list[index].groupId,
                    "coinAddress":this.formEdit.list[index].walletAddress
                }
                delWalletAddress(para).then(data=>{
                    // console.log(data)
                    //  console.log(para)
                    this.$message({
                        message: data.data.message,
                        type: 'success'
                        });
                        if(data.success==true){
                            this.formEdit.list.splice(index,1)
                        }
                    this.getcoin(this.currentPage)
                })
                
                
            })
            .catch(_ => { console.log("取消")});
        },
        lookEr(value){
            
            this.erweima=true;
            this.eaddress=value;
            setTimeout(()=>{
                let qrcode = new this.QRCode('qrcodeNode', {
                    width: 180,
                    height: 180,
                    colorDark: "#000000",
                    colorLight: "#ffffff"
                });
                qrcode.makeCode(value);
            },1)
            
        },
        handleCurrentChange(val){
            // console.log(val)
            this.currentPage=val;
            this.getcoin(this.currentPage);
        },
        handleClose(){
            document.getElementById('qrcodeNode').innerHTML = "";
            this.erweima=false;
        },
        submit1(){
            
            // console.log(this.formEdit)
            let para={
                'id':this.formEdit.id,
                'groupName':this.formEdit.groupName
            }
            becameWallet(para).then(data=>{
                this.dialogEdit=false;
                // console.log(this.currentPage)
                this.getcoin(this.currentPage);
                 this.$message({
                    message: data.data.message,
                    type: 'success'
                    });
                console.log(data)
            })
        }
    }
}

</script>
<style>

.el-table tr th:first-child{
    width: 80px;
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
.small .el-dialog--small{
    width: 20%;
    text-align: center;
}
.small .el-dialog--small img{
    display: block;
    margin: 0 auto;
}
</style>
