import router from '@/router'
import { getUserInfo, login, selectRole } from '@/service/user'
import { loadRouter } from '@/utils/loadCpn'
import { message } from 'ant-design-vue'
import { defineStore } from 'pinia'
import { Md5 } from 'ts-md5'
import { computed, ref } from 'vue'

export const userStore = defineStore(
  'user',
  () => {
    const token = ref('')
    const userInfo = ref({})
    const userMenus = ref([])
    const permissions = ref([])

    const selectKey = ref(null)

    const isLoading = ref(false)

    const isPush = ref(false)

    // getter
    const accessToken = computed(() => 'Bearer ' + token.value)

    // setup store 不提供$reset 需要自己重置
    // https://github.com/vuejs/pinia/issues/1056
    const $reset = () => {
      token.value = ''
      userInfo.value = {}
      userMenus.value = []
      permissions.value = []
    }

    /**
     * 获取用户信息 & 菜单路由
     * @param {*} uid 用户id
     */
    const getUserData = async (uid) => {
      // 2. 获取用户信息
      const info = await getUserInfo(uid)
      userInfo.value = info.userInfo

      // 3.1 加载路由权限
      loadRouter(info.roleList)

      // 3.2 默认打开菜单
      // selectKey.value = [firstMenu.id]

      // 4. 跳转路由
      // if (firstMenu?.path) {
      //   router.push(firstMenu.path)
      // } else {
      //   router.push('/main')
      // }
      router.push('/main')
    }

    const loginAction = async (data) => {
      // 密码加密
      const info = {
        "account": data.account,
        "captcha": data.captcha
      }
      info.password = Md5.hashStr(data.account+"&"+data.password)
      console.log(111)
      // 1. 登录
      const res = await login(info)
      token.value = res.token
      await getUserData(res.id)
      // 弹框提示登录成功
      message.success('登录成功.')
    }

    // loadRouter 刷新问题
    const loadRoleRouter = () => {
      loadRouter(userMenus.value)
    }

    // 切换角色
    const userSelectRole = async (rid) => {
      await selectRole(rid)
      // 重新拿用户信息
      await getUserData(userInfo.value.id)
    }

    return {
      isPush,
      token,
      accessToken,
      userInfo,
      userMenus,
      permissions,
      isLoading,
      selectKey,
      $reset,
      getUserData,
      loginAction,
      loadRoleRouter,
      userSelectRole
    }
  },
  {
    persist: true
  }
)
