<template>
  <div>
      <h2>添加账户</h2>
    <el-form ref="form" :model="form" label-width="180px">
        <el-form-item label="用户名" class="passworld">
            <el-input  v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="初始密码" class="passworld">
            <el-input  v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名" class="passworld">
            <el-input  v-model="form.realName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" class="passworld">
            <el-input  v-model="form.phone" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm()">创建</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { addLogin } from '../../api/api';
  export default {
    data() {
      return {
        form: {
          name:'',
          password:'',
          realName:'',
          phone:''
        }
      }
    },
    methods: {
      submitForm() {
        this.$confirm('确认使用此密码？')
            .then(_ => {
                var para=this.form;
            
                // console.log(para)
                
                addLogin(para).then(data =>{
                 console.log(data)
                    this.$message({
                        message: data.data.message,
                        type: 'success'
                        });
                })
            })
            .catch(_ => { 
                console.log("取消")
            });
      }
    }
  }
  

</script>
<style>
.passworld{
    width: 30%;
}
</style>
