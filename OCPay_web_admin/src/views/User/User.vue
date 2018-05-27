<template>
  <div>
      <h2>修改密码</h2>
    <el-form ref="form" :model="form" label-width="180px">
        <el-form-item label="修改新密码" class="passworld">
            <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm()">提交</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { becamePass } from '../../api/api';
  export default {
    data() {
      return {
        form: {
          name:'',
          password:''
        }
      }
    },
    methods: {
      submitForm() {
        this.$confirm('确认使用此密码？')
            .then(_ => {
                let para={
                  "name":sessionStorage.getItem('account'),
                  "password":this.form.password
                }
                console.log(para)
                
                becamePass(para).then(data =>{
                  // sessionStorage.removeItem('user');
                  // this.$router.push('/')
                  console.log(data)
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
