<template>
<div>
   <h3>Windows</h3>
    <hr/>  
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="No"
      label="No"
      width="">
    </el-table-column>
    <el-table-column
      prop="ID"
      label="ID"
      width="">
    </el-table-column>
    <el-table-column
      prop="rank"
      label="rank"
      width="">
    </el-table-column>
    <el-table-column
      prop="Content"
      label="Content"
      width="">
    </el-table-column>
    <el-table-column
      prop="LINK"
      label="LINK"
      width="">
    </el-table-column>
    <el-table-column
      prop="Sequential "
      label="Sequential"
      width="">
    </el-table-column>
    <!-- 创建开关 -->
    <el-table-column label="Sequential" >
				<template slot-scope="scope">
                 <el-button size="small" @click="Slook(scope.$index, scope.row)">EDIT</el-button>
                 <el-button size="small" >Delete</el-button>
				</template>
			</el-table-column>                  
   </el-table>
    <!-- 创建开关表单 -->
    <el-dialog
        title=""
        :visible.sync="dialogVisible"
        width="30%">
       <el-form ref="form" :model="form" label-width="80px"> 
             <el-form-item label="Module Name ID">
             <el-input v-model="form.name">Module Name ID</el-input>
             </el-form-item>

             <el-form-item label="Rank">
             <el-input v-model="form.name">number</el-input>
             </el-form-item>

              <el-form-item label="Content">
             <el-input v-model="form.name">备注</el-input>
             </el-form-item>

             <el-form-item label="Access type">
                <el-select v-model="form.region" placeholder="CHOOSE ONE">
                    <el-option label="CHOOSE ONE" value="CHOOSE ONE"></el-option>
                    <el-option label="internal Link" value="internal Link"></el-option>
                    <el-option label="inbound Link" value="inbound Link"></el-option>
                    <el-option label="3TH MIRC" value="3TH MIRC"></el-option>
                    <el-option label="Link " value="Link"></el-option>
                </el-select>
           </el-form-item>
           <el-button type="primary">提交</el-button>
        </el-form>     
 
     </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          No: '1',
          ID: '1',
          rank: '1',
          Content: 'obike',
          LINK: '跳转页面编码',
          Sequential : ''
        }],
         dialogVisible: false ,
         form:{
           name:"",
           region:""
         }
      };     
    },
    methods:{
       Slook(index,row){
            this.dialogVisible=true;
            this.form=row;
        //    this.$store.state.count=row;
        },
    }
  }
</script>
<style>
.el-dialog--small {
    width: 45%;
}

</style>
