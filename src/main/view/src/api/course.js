import request from '@/utils/request'
import qs from 'qs'

// 练习题列表
export function getPaperList(data) {
  return request({
    url: '/PaperList',
    method: 'post',
    data: data
  })
}
// 练习主页面
export function GetExamPage(data) {
  return request({
    url: '/GetExamPage',
    method: 'post',
    // headers: {
    //   'Content-type': 'application/x-www-form-urlencoded'
    // },
    data: data
  })
}
// 提交练习
export function PostExamPage(data) {
  return request({
    url: '/PostExamPage',
    method: 'post',
    data: data
  })
}
// 练习题列表（管理端）
export function GetTopicList(data) {
  return request({
    url: '/admin/GetTopicList',
    method: 'post',
    data: data
  })
}
// 练习题列表（管理端）
export function GetTopic(data) {
  return request({
    url: '/admin/GetTopic',
    method: 'post',
    data: data
  })
}
// 练习题列表（管理端） 新增
export function AddTopic(data) {
  return request({
    url: '/admin/AddTopic',
    method: 'post',
    data: data
  })
}
// 练习题列表（管理端） 更新
export function UpdateTopic(data) {
  return request({
    url: '/admin/UpdateTopic',
    method: 'post',
    data: data
  })
}
// 练习题列表（管理端） 更新
export function TopicStatus(data) {
  return request({
    url: '/admin/TopicStatusModify',
    method: 'post',
    data: data
  })
}
// 搜索题⽬
export function SearchTopic(data) {
  return request({
    url: '/SearchTopic',
    method: 'post',
    data: data
  })
}

// 收藏题目
export function FavTopic(data) {
  return request({
    url: '/FavTopic',
    method: 'post',
    data: data
  })
}

//获取顺序做题
export function GetSequenceExam(data) {
  return request({
    url: '/GetSequenceExam',
    method: 'post',
    data: data
  })
}

//获取课程清单
export function GetExams(data) {
  return request({
    url: '/GetExams',
    method: 'post',
    data: data
  })
}

//顺序做题提交
export function PostSequenceExam(data) {
  return request({
    url: '/PostSequenceExam',
    method: 'post',
    data: data
  })
}

//顺序做题提交
export function SequenceExams(data) {
  return request({
    url: '/SequenceExams',
    method: 'post',
    data: data
  })
}

//顺序做题重做
export function RedoSequenceExam(data) {
  return request({
    url: '/RedoSequenceExam',
    method: 'post',
    data: data
  })
}

//视频列表
export function VideoList(data) {
  return request({
    url: '/VideoList',
    method: 'post',
    data: data
  })
}

// 课程兑换
export function ExchangeCode(data) {
  return request({
    method: 'POST',
    url: '/ExchangeCode',
    data: data
  })
}
// 获取兑换码
export function GetExchangeCode(data) {
  return request({
    method: 'POST',
    url: '/admin/GetExchangeCode',
    data: data
  })
}

// 筛选条件
export function GetFilterList(data) {
  return request({
    method: 'POST',
    url: '/GetFilterList',
    data: data
  })
}

// 答题卡
export function examSequence(data) {
  return request({
    method: 'POST',
    url: '/examSequence',
    data: data
  })
}
