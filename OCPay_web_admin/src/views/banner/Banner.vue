<template>
 <div>
      <h3>Banner</h3>
    <hr/>
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
      prop="showSort"
      label="showSort"
      width="">
    </el-table-column>
    <el-table-column
      prop="title"
      label="rank"
      width="">
    </el-table-column>
    
    <el-table-column
      label="Pic"
      width="">
      	<template slot-scope="scope">
                 <img :src="scope.row.showImg" alt="">
				</template>
    </el-table-column>
    <el-table-column
      prop="content"
      label="Content"
      width="">
    </el-table-column>
    <el-table-column
      prop="directUrl"
      label="LINK"
      width=""> 
    </el-table-column>
     <!-- 创建两个开关 -->
   <el-table-column  prop="showSort" label="Sequential" >
				<template slot-scope="scope">
                 <el-button size="small" @click="Slook(scope.$index, scope.row)">EDIT</el-button>
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
    <!-- 编辑 -->
    <el-dialog
        title=""
        :visible.sync="dialogVisible"
        width="30%">
       <el-form ref="form" :model="form" label-width="80px"> 
             <el-form-item label="ID">
                 <span>{{form.id}}</span>
             </el-form-item>
             <el-form-item label="rank">
             <el-input v-model="form.title"></el-input>
             </el-form-item>
              <el-form-item label="Content">
             <el-input v-model="form.content"></el-input>
             </el-form-item>
             <el-form-item label="Access type">
                <el-select v-model="form.directType" placeholder="internal Link">
                     <el-option label="internal Link" value="internal Link"></el-option>
                     <el-option label="inbound Link" value="inbound Link"></el-option>
                     <el-option label="3TH MIRC" value="3TH MIRC"></el-option>
                    <el-option label="SHOP Link" value="SHOP Link"></el-option>
                 </el-select>    
           </el-form-item>
           <el-form-item label="LINK">
             <el-input v-model="form.directUrl"></el-input>
             </el-form-item>
           <el-form-item label="Pic">
              <el-col :span="18">
                 <el-input v-model="form.showImg" disabled=""></el-input>
             </el-col>
             <el-col :span="6">
               <el-button type="primary" @click="upload">上传图片</el-button>
               <input class="img1" @change="uploadImg()" ref="img" type="file">
             </el-col>
           </el-form-item>
            <el-form-item label="showSort">
             <el-input v-model="form.showSort"></el-input>
           </el-form-item>
         <div class="button">
                <el-button type="primary" @click="edit">提交</el-button>
             </div>       
        </el-form>       
</el-dialog>
<!-- 添加 -->
  <el-dialog
        title=""
        :visible.sync="dialogVisible2"
        width="30%">
       <el-form ref="form" :model="form1" label-width="80px"> 
             <el-form-item label="homePageId">
               <el-select v-model="form1.homePageId" placeholder="">
                     <el-option v-for="(item,index) in idArr" :key="index" :label="item.content" :value="item.id"></el-option>                   
              </el-select>   
             </el-form-item>
             <el-form-item label="rank">
             <el-input v-model="form1.title"></el-input>
             </el-form-item>
              <el-form-item label="Content">
             <el-input v-model="form1.content"></el-input>
             </el-form-item>
<!-- 下拉菜单 -->
             <el-form-item label="directType">
                   <el-select v-model="form1.directType" placeholder="internal Link">
                     <el-option label="跳入第三方APP" value="1"></el-option>
                     <el-option label="调转APP内页" value="2"></el-option>
                     <el-option label="跳转到新网址" value="3"></el-option>
                 </el-select>
          </el-form-item>
           <el-form-item label="LINK">
             <el-input v-model="form1.directUrl"></el-input>
             </el-form-item>
           <!-- <el-form-item label="Pic">
             <el-input v-model="form1.showImg"></el-input>
           </el-form-item> -->
            <el-form-item label="Pic">
              <el-col :span="18">
                 <el-input v-model="form1.showImg" disabled=""></el-input>
             </el-col>
             <el-col :span="6">
               <el-button type="primary" @click="upload1">上传图片</el-button>
               <input class="img1" @change="uploadImg1()" ref="img2" type="file">
             </el-col>
           </el-form-item>
            <el-form-item label="showSort">
             <el-input v-model="form1.showSort"></el-input>
           </el-form-item>            
            <div class="button">
                <el-button type="primary" @click="submitadd()">提交</el-button>
            </div>       
        </el-form>       
</el-dialog>
     
 </div>
</template>

<script>
import {Banner,BannerEdit,BannerDelete,BannerAdd,BannerGet,upload} from '../../api/api';

  export default {
    data() {
      return {    
        tableData: [],
         dialogVisible: false,
         dialogVisible2: false,
         form:{
         },
         form1: { 
           showImg:"",
           homePageId:"",
           title:"",
           content:"",
           directType:"",
           directUrl:"",
           showSort:""
          },
        
        //默认每页数据量
        pagesize: 10,
        //当前页码 
        currentPage: 1,
        //默认数据总数
        totalCount: 20,
        idArr:[]       
      };   
    },
    created(){
      this.git();
    },
     mounted(){
         this.getcoin(this.currentPage)    
    },
     methods: {
       uploadImg(){
         let para=new FormData();
         para.append("file",this.$refs.img.files[0])
        //  console.log()
         upload(para).then(data=>{
          //  console.log(data)
          this.form.showImg=data.data.message;
         })
       },
       upload(){
         this.$refs.img.click();
       },


      uploadImg1(){
         let para=new FormData();
         para.append("file",this.$refs.img2.files[0])
        //  console.log()
         upload(para).then(data=>{
           console.log(data)
           this.form1.showImg=data.data.message;
           console.log(this.form1.showImg)
         })
       },
       upload1(){
         this.$refs.img2.click();
       },
      //  查询表格数据
          getcoin(num){
            let para={
              		"type":"1",
                  "pageNum":num,
                   "pageSize":"10"
              }
            
            Banner(para).then(data=>{
                // console.log(data)
                this.tableData=data.data.list;
                this.totalCount=data.data.count;
            })  
        },
        // 编辑数据
        edit(){
          let para={
              id:this.form.id ,
              homePageId:this.form.homePageId,
              content:this.form.content,
              directType:this.form.directType,
              directUrl:this.form.directUrl,
              showImg:this.form.showImg,
              showSort:this.form.showSort,
              title:this.form.rank
            }
          BannerEdit(para).then(data=>{
                // console.log(data)
                this.getcoin(this.currentPage);
                this.dialogVisible=false;
            })
        },
        // 删除
        delete1(row){
           let para={
             id:row.id,
             
            }
         BannerDelete(para).then(data=>{
                // console.log(data)
                this.getcoin(this.currentPage);    
            })     

        },
         // 获取BannerGet
        git(){
          
          BannerGet().then(data=>{
                // console.log(data)
                this.idArr=data.data;
            })
        },
        // 添加
        add(){
          this.dialogVisible2=true;
        },
        submitadd(){    
            let para={                        	
	            "homePageId":this.form1.homePageId,
              "directType":this.form1.directType,
              "showSort":this.form1.showSort,
              "showImg":this.form1.showImg,
               "content":this.form1.content,
               "title":this.form1.title,
               "directUrl":this.form1.directUrl
             }
             BannerAdd(para).then(data=>{
                // console.log(data)
                this.dialogVisible2=false;
                this.getcoin(this.currentPage);    
            })
        },
         Slook(index,row){
            this.dialogVisible=true;
            this.form=row;
            console.log(this.form)
        //    this.$store.state.count=row;
        },
        handleCurrentChange(val){
          //  console.log(val)
          this.currentPage=val;
          this.getcoin(val)
        }
    }
  }
</script>
<style>
.el-dialog--small {
    width: 45%;
}
.button {
    text-align: center;
}
.img1{
  display: none;
}
.el-table .cell{
  text-align: center;
}
.el-table .cell img{
  max-width: 100%;
  max-height:100px ;
}
</style>
