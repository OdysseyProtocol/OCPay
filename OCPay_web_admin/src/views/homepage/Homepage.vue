<template>
<div>
  <h3>Homepage</h3>
    <hr/>
    <!-- 添加开关 -->
    <el-form  class="demo-form-inline wallet">
        <el-button type="primary" @click="add">ADD</el-button>
    </el-form>  
    <hr/>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      label="No.">
      <template slot-scope="scope1">             
					{{scope1.$index+1+10*(currentPage-1)}}
				</template>
    </el-table-column>
    <el-table-column
      prop="id"
      label="ID"
      width="">
    </el-table-column>
    <el-table-column
      prop="content"
      label="Content"
      width="">
    </el-table-column>
    <el-table-column
      label="Module type">
        <template slot-scope="scope">
          <span v-if="scope.row.type==1">小程序</span>
          <span v-if="scope.row.type==2">横屏广告</span>
          <span v-if="scope.row.type==3">侧栏广告</span>
				</template>
    </el-table-column>
    <el-table-column
      prop="sort"
      label="Sequential"
      width="">
    </el-table-column>
<!-- 创建Sequential Delete -->
    <el-table-column label="Sequential" >
				<template slot-scope="scope">
           <el-button size="small" @click="look(scope.row)">Edit</el-button>
           <el-button size="small" @click="delete1(scope.row)">Delete</el-button>
				</template>
			</el-table-column>
   </el-table>
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

   <!-- 创建Sequentia下编辑开关 -->
   <el-dialog
         title=""
        :visible.sync="dialogVisible"
        class="dig"
         width="30%">
        <el-form ref="form" :model="form" label-width="120px">
              <el-form-item label="Module Name ID">
                    <!-- <el-input v-model="form.id"></el-input> -->
                    <span>{{form.id}}</span>
              </el-form-item>
              <el-form-item label="Content">
                    <el-input v-model="form.content"></el-input>
              </el-form-item>

              <el-form-item label="Module Type">
                  <el-select v-model="form.region" v-if="form.type==1" placeholder="小程序">
                    <el-option label="小程序" value="1"></el-option>
                    <el-option label="横屏广告" value="2"></el-option>
                    <el-option label="侧栏广告" value="3"></el-option>          
                  </el-select>
                  <el-select v-model="form.region" v-if="form.type==2" placeholder="横屏广告">
                    <el-option label="小程序" value="1"></el-option>
                    <el-option label="横屏广告" value="2"></el-option>
                    <el-option label="侧栏广告" value="3"></el-option>          
                  </el-select>
                  <el-select v-model="form.region" v-if="form.type==3" placeholder="侧栏广告">
                    <el-option label="小程序" value="1"></el-option>
                    <el-option label="横屏广告" value="2"></el-option>
                    <el-option label="侧栏广告" value="3"></el-option>          
                  </el-select>
              </el-form-item>
              <el-form-item label="sort">
                    <el-input v-model="form.sort"></el-input>
              </el-form-item>
              <div class="button"><el-button type="primary" @click="edit">提交</el-button></div>
                    
        </el-form>
</el-dialog>
<!-- 创建ADD开关 -->
      <el-dialog
         title=""
        :visible.sync="dialogVisible2"
        class="dig"
         width="30%">
        <el-form ref="form" :model="form1" label-width="120px">
              
              <el-form-item label="Content">
                    <el-input v-model="form1.content"></el-input>
              </el-form-item>

             <el-form-item label="Module Type">
                <el-select v-model="form1.type" placeholder="Module Type">
                   <el-option label="小程序" value="1"></el-option>
                    <el-option label="横屏广告" value="2"></el-option>
                    <el-option label="侧栏广告" value="3"></el-option>       
               </el-select>
             </el-form-item>

              <el-form-item label="sort">
                    <el-input v-model="form1.sort"></el-input>
              </el-form-item>
              <div class="button"><el-button type="primary" @click="submitadd">提交</el-button></div>
                    
        </el-form>
</el-dialog>

</div>
</template>

<script>
import { Homepage,HomepageEdit,HomepageDelete,HomepageAdd} from '../../api/api';
  export default {
    data() {
      return {
        tableData: [{
          No: '',
          ID: '',
          Content: '',
          Module: '',
          Sequential : ''
        }
        ],
      //  定义并隐藏
        dialogVisible:false,
        dialogVisible2:false,

        form: {
          name: '',
          region: '' 
        },
        form1: {
          type:"",
          sort:"",
          content:""
        },
        //默认每页数据量
        pagesize: 10,
        //当前页码 
        currentPage: 1,
        //默认数据总数
        totalCount: 20,
   
      }
    },
   mounted(){
         this.getcoin(this.currentPage)
         
    },
    methods:{
   // 表格数据
          getcoin(num){
           
            let para={
                "pageNum":num,
                "pageSize":this.pagesize
              }
            
            Homepage(para).then(data=>{
                console.log(data)
                this.tableData=data.data.list;
                this.totalCount=data.data.count;
            })  
        },
    // 编辑数据
        edit(){
          let para={
              id:this.form.id,
              type:this.form.region,
              sort:this.form.sort,
              content:this.form.content
            }
          HomepageEdit(para).then(data=>{
                // console.log(data)
                this.getcoin(this.currentPage);
                this.dialogVisible=false;
            })
        },
        // 删除数据
        delete1(row){
           let para={
             "id":row.id,      
            }
          HomepageDelete(para).then(data=>{
                // console.log(data)
                this.getcoin(this.currentPage);    
            })     

        },
        look(row){
            this.dialogVisible=true;
            this.form=row;
            // console.log(row)
        //    this.$store.state.count=row;
        },
        // 增加数据
        add(){
          this.dialogVisible2=true;
      
        },
        submitadd(){
          
           let para=this.form1;
             HomepageAdd(para).then(data=>{
                // console.log(data)
                this.dialogVisible2=false;
                this.getcoin(this.currentPage);    
            })
        },
         handleCurrentChange(val){
            // console.log(val)
            this.currentPage=val;
            this.getcoin(val)
        },

      }
  }
</script>
<style>
.dig .el-dialog--small{
  width: 35%;
}
.el-button{
  text-align: center;
}
/* .el-button--primary{
  margin-left: 185px;
}
.wallet .el-button--primary */
.button{
  text-align: center;
}
</style>
