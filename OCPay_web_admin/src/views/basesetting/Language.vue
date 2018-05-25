<template>
    <div>
        <h3>Language</h3><hr>
        <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="No"
        label="No"
        width="180">
      </el-table-column>
      <el-table-column
        prop="abb"
        label="abb"
        width="180">
      </el-table-column>
      <el-table-column
        prop="ID"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="CONTENT"
        label="CONTENT">
      </el-table-column>
      <el-table-column
        prop="Icon"
        label="Icon">
      </el-table-column>
      <el-table-column
        prop="State"
        label="State">
      </el-table-column>
      <el-table-column
        prop="IP"
        label="IP">
      </el-table-column>
      <el-table-column
        prop="Operat "
        label="Operat ">
      </el-table-column>
        <!-- 创建表单 -->
    <el-table-column label="Operat" >
				<template slot-scope="scope">
                    <el-button size="small" @click="Slook(scope.$index, scope.row)">EDIT</el-button>
                   <el-button size="small" >Delete</el-button>
				</template>
	 </el-table-column>
    </el-table>
    <el-dialog
        title=""
        :visible.sync="dialogVisible"
        width="30%">
       <el-form ref="form" :model="form" label-width="80px"> 
             <el-form-item label="Language abb.">
             <el-input v-model="form.name"></el-input>
             </el-form-item>
             <el-form-item label="Language ID">
             <el-input v-model="form.name"></el-input>
             </el-form-item>
              <el-form-item label="CONTENT">
             <el-input v-model="form.name"></el-input>
             </el-form-item>
             <el-form-item label="Language Icon">
             <el-input v-model="form.name"></el-input><span></span>
             </el-form-item>
              <el-button type="primary" >立即创建</el-button>    
        </el-form>       
       </el-dialog>   
    </div>
    
</template>
<script>
      export default {
      data() {
        return {
          tableData: [{
            No: '',
            abb: '',
            Name: '',
            CONTENT: '',
            Icon: '',
            State: '',
            Scale: '',
            Deal: '',
    
          }, ],
           form:{
           name:"",
           region:""
         },      
          
          dialogVisible:false
        }
      },
      methods:{
          Slook(index,row){
            this.dialogVisible=true;
            this.form=row;
        },
      }
    }
</script>
