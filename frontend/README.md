# 校园通前端

Vue 3 + TypeScript + Vite 前端脚手架，面向“校园服务智能助手（校园通）”。

## 技术栈

- Vue 3
- TypeScript
- Vite
- Vue Router
- Pinia
- Axios
- SCSS

## 目录结构

```text
src/
├─ api/                 # 接口管理
├─ assets/              # 图片与全局样式
├─ components/          # 公共组件
│  ├─ chat/
│  ├─ procedure/
│  └─ common/
├─ router/              # 路由
├─ store/               # Pinia 状态管理
├─ types/               # TypeScript 类型
├─ views/               # 页面
└─ utils/               # 工具函数
```

## 启动

```bash
npm install
npm run dev
```

默认前端地址：

`http://127.0.0.1:5173`

开发环境下 `/api` 会代理到：

`http://127.0.0.1:8000`

对应 FastAPI 后端。

## 当前脚手架说明

当前版本重点是项目结构和前后端接口位置，不包含完整的真实业务实现。后续可继续接入：

- RAG 问答
- 来源引用
- FAQ 管理
- 办事流程
- 工具调用
- FAQ 评测
