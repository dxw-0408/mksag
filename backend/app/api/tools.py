from datetime import datetime

from fastapi import APIRouter

router = APIRouter(prefix="/api/tools", tags=["工具调用"])


@router.get("/library-hours")
def library_hours():
    return {
        "available": False,
        "message": "图书馆开放时间接口尚未接入，请后续配置学校官方数据源。",
    }


@router.get("/server-time")
def server_time():
    return {"server_time": datetime.now().isoformat()}
