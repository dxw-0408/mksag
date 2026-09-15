# 校园通 FastAPI 后端脚手架

这是“校园服务智能助手（校园通）”项目的后端第一版脚手架，技术路线为：

- FastAPI：提供 REST API
- RAG：文档解析 → 分块 → 混合检索 → Rerank → 大模型生成
- 大模型 API：后续接入具体模型服务
- 来源溯源：答案保留 sources 字段
- 防幻觉：没有可靠资料时明确拒答
- 工程化：缓存、限流、日志、评测模块均预留

## 1. 目录结构

```text
backend/
├─ app/
│  ├─ main.py
│  ├─ api/              # FastAPI 路由
│  ├─ models/           # 请求/响应数据模型
│  ├─ services/         # 业务服务
│  ├─ rag/              # RAG 核心链路
│  ├─ core/             # 配置、缓存、限流、日志
│  └─ db/               # 数据库连接
├─ data/
│  ├─ documents/        # 后续放学校公开资料
│  └─ faq/              # 后续放 FAQ 数据
├─ tests/
├─ .env.example
├─ .gitignore
├─ requirements.txt
└─ README.md
```

## 2. Windows + PowerShell 启动

在 `backend` 目录执行：

```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
uvicorn app.main:app --reload
```

启动成功后打开：

- `http://127.0.0.1:8000/`
- `http://127.0.0.1:8000/health`
- `http://127.0.0.1:8000/docs`

## 3. 当前接口

- `POST /api/chat/ask`：校园问答
- `GET /api/faq`：FAQ 列表
- `POST /api/faq`：新增 FAQ
- `GET /api/procedures`：办事流程
- `GET /api/evaluation/summary`：评测概览
- `GET /api/tools/library-hours`：图书馆时间接口占位
- `GET /api/tools/server-time`：服务端时间

## 4. 当前阶段说明

这是“项目脚手架”，不是最终业务版本。RAG 检索、向量数据库、Rerank、大模型 API、真实校园数据源将在后续迭代中接入。

特别注意：正式开发时请把真实 API Key 写入 `.env`，不要提交到 Git 仓库。
