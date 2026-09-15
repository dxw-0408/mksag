import logging


logger = logging.getLogger("campus_assistant")
if not logger.handlers:
    logging.basicConfig(level=logging.INFO)
