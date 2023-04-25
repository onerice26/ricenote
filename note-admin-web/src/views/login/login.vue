<script setup>
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import { ref, reactive, computed } from 'vue'

import { userStore } from '@/stores/user'
import { commStore } from '@/stores/comm'
import { loginRules } from './conf'

const store = userStore()
const comm = commStore()

// 响应式数据
const formRef = ref()
const formData = reactive({
  account: '',
  password: '',
  captcha: ''
})
// 计算属性 登录按钮是否可以点击
const disabled = computed(() => {
  return !(formData.account && formData.password)
})

// 事件
const submitForm = (formEl) => {
  if (!formEl) return
  formEl.validate().then(() => store.loginAction(formData))
}
const captchaUrl = comm.refreshCaptcha();
</script>

<template>
  <div class="login">
    <div class="continer">
      <h1>Mini RBAC</h1>

      <a-form ref="formRef" :model="formData" :rules="loginRules">
        <a-form-item has-feedback name="account">
          <a-input v-model:value.trim="formData.account" placeholder="请输入用户名">
            <template #prefix>
              <UserOutlined style="color: rgba(0, 0, 0, 0.25)" />
            </template>
          </a-input>
        </a-form-item>
        <a-form-item has-feedback name="password">
          <a-input-password
            v-model:value.trim="formData.password"
            placeholder="请输入密码"
            autocomplete="on"
          >
            <template #prefix>
              <LockOutlined style="color: rgba(0, 0, 0, 0.25)" />
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item>
                        <el-input
                            class="yzminput"
                            v-model="formData.captcha"
                            placeholder="验证码, 单击图片刷新"
                        ></el-input>
          <img
                            class="yzm"
                            :src="captchaUrl"
                            alt="验证码"
                            style="width: 65px; height: 35px"
                            @click="getCaptcha"
                        />
        </a-form-item>
        <a-form-item>
          <a-button
            type="primary"
            html-type="submit"
            :disabled="disabled"
            @click="submitForm(formRef)"
            >登录</a-button
          >
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  width: 100%;
  height: 100%;
  background-image: url('@/assets/img/background.svg');
}
.continer {
  width: 300px;
  height: 300px;
}
.continer h1 {
  /* color: #fff; */
  margin-bottom: 30px;
}
.continer .ant-btn {
  width: 100%;
}
.yzm {
    margin-top: 4px;
    margin-right: 12px;
    float: left;
}

.yzminput {
    width: 165px;
}
</style>
