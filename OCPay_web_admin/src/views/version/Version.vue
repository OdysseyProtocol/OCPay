<template>
<div>
  <h3>Version</h3>
   <hr/>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="No"
      label="No."
      width="">
    </el-table-column>
    <el-table-column
      prop="ID"
      label="Version ID"
      width="">
    </el-table-column>
    <el-table-column
      prop="Name"
      label="Version Name"
      width="">
    </el-table-column>
    <el-table-column
      prop="Time"
      label="Intention Release Time"
      width="">
    </el-table-column>
    <el-table-column
      prop="Booking "
      label="Booking Release"
      width="">
    </el-table-column>
     <el-table-column
      prop="Operator"
      label="Operator"
      width="">
    </el-table-column>
     <el-table-column
      prop="Content"
      label="Content"
      width="">
    </el-table-column>
     <el-table-column
      prop="Release"
      label="Release"
      width="">
    </el-table-column>
     <el-table-column
      prop="Operation"
      label="Operation"
      width="">
    </el-table-column>
    <el-table-column label="Operation" >
				<template slot-scope="scope">
                 <el-button size="small" @click="Versionview(scope.$index, scope.row)">VIEW</el-button>
                 <el-button size="small" @click="Versionadd(scope.$index, scope.row)">ADD</el-button>
                 <el-button size="small" @click="Versionedit(scope.$index, scope.row)">EDIT</el-button>
				</template>
		</el-table-column>   
  </el-table>
  <!-- 创建Versionview表单 -->
    <el-dialog
        title=""
        :visible.sync="dialogVisible"
        width="30%">
       <el-form ref="form" :model="form" label-width="80px"> 
             <el-form-item label="No.">
             <span>112</span>
             </el-form-item>
             <el-form-item label="Version ID.">
             <span>0.20</span>
             </el-form-item>
             <el-form-item label="Version Name">
             <span>0.20</span>
             </el-form-item>
             <el-form-item label="Intention Release Time">
             <span>2014.09.01 12:00:00</span>
             </el-form-item>
             <el-form-item label="Content">
            <el-input type="textarea" v-model="form.desc">Content</el-input>
           </el-form-item>
            <hr/>
            <el-form-item label="Update record">
           </el-form-item>
           <template>
             <el-table
             :data="tablesonData"
              border
              style="width:100%">
              <el-table-column
               prop="Time"
               label="Update Time"
              width="180">
              </el-table-column>
              <el-table-column
              prop="Operator"
             label="Operator"
             width="180">
            </el-table-column>
             <el-table-column
             prop="Actions"
             label="Actions">
            </el-table-column>
    </el-table>
  </template>
        </el-form>                
</el-dialog>
<!-- 创建Versionadd表单 -->
  <el-dialog
         title=""
        :visible.sync="dialogAdd"
        class="dig"
         width="30%">
         <el-form ref="form" :model="form" label-width="80px">
           <el-form-item label="Version ID">
               <el-input v-model="form.name">0.20</el-input>
           </el-form-item>
           <el-form-item label="Version Name">
               <el-input v-model="form.name">0.20</el-input>
           </el-form-item>
           <el-form-item label="Intention Release Time">
               <el-input v-model="form.name">2014.09.01 12:00:00</el-input>
           </el-form-item>
           <el-form-item label="Content" class="conetnt">
               <el-input type="textarea" v-model="form.desc"></el-input>
           </el-form-item>


           </el-form>
        
</el-dialog>
<!-- 创建Versionedit表单 -->
<el-dialog
         title=""
        :visible.sync="dialogEdit"
        class="dig"
         width="40%">
         <el-form ref="form" :model="form" label-width="80px">
           <el-form-item label="Version ID">
               <el-input v-model="form.name">0.20</el-input>
           </el-form-item>
           <el-form-item label="Version Name">
               <el-input v-model="form.name">0.20</el-input>
           </el-form-item>
           <el-form-item label="Intention Release Time">
               <el-input v-model="form.name">2014.09.01 12:00:00</el-input>
           </el-form-item>
           <el-form-item label="Content" class="edcontent">
               <el-input type="textarea" v-model="form.desc"></el-input>
           </el-form-item>


           </el-form>
        
</el-dialog>

   
</div>
  
</template>

<script>
  export default {
    data() {
      return {
        tablesonData:[{
            Time	:'',
           Operator:'',
           Actions:'',
        }],
        tableData: [{
          No: '1',
          ID: '',
          Name: '',
          Time: '',
          Booking : '',
          Operator: '',
          Content: '',
          Release: '',
          Operation: '',
        },
        {
          No: '2',
          ID: '',
          Name: '',
          Time: '',
          Booking : '',
          Operator: '',
          Content: '',
          Release: '',
          Operation: '',
        },
        {
          No: '3',
          ID: '',
          Name: '',
          Time: '',
          Booking : '',
          Operator: '',
          Content: '',
          Release: '',
          Operation: '',
        },
        {
          No: '4',
          ID: '',
          Name: '',
          Time: '',
          Booking : '',
          Operator: '',
          Content: '',
          Release: '',
          Operation: '',
        },
        {
          No: '5',
          ID: '',
          Name: '',
          Time: '',
          Booking : '',
          Operator: '',
          Content: '',
          Release: '',
          Operation: '',
        }
        ],
         dialogVisible: false ,
         dialogAdd: false ,
         dialogEdit:false,
         form:{
           name:"",
           region:""
         }
      }
  
    },
     methods:{
       Versionview(index,row){
            this.dialogVisible=true;
            this.form=row;
        //    this.$store.state.count=row;
        },
        Versionadd(index,row){
            this.dialogAdd=true;
            this.form=row;
        //    this.$store.state.count=row;
        },
        Versionedit(index,row){
            this.dialogEdit=true;
            this.form=row;
        //    this.$store.state.count=row;
        },
    }
  }
</script>
<style>
.conetnt .el-textarea__inner{
  height:200px ;
}
.edcontent .el-textarea__inner{
  height:200px ;
}

</style>
