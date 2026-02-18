import time
import redis
from flask import Flask

# Create a new Flask web application
app = Flask(__name__)

# Connect to the Redis database container (hostname: "redis")
# Redis listens on its default port 6379.
cache = redis.Redis(host='redis', port=6379)

def get_hit_count():
   """
   Tries to increment a counter ('hits') stored in Redis.
   If Redis is not reachable yet (for example: container still starting),
   the function will retry up to 5 times with a 0.5-second delay.
   """
   retries = 5
   while True:
      try:
            # Increment the value of the 'hits' key and return the new value
            return cache.incr('hits')
      except redis.exceptions.ConnectionError as exc:
            # If Redis is unavailable: retry until no retries remain
            if retries == 0:
               # No retries left → raise the exception
               raise exc
            retries -= 1
            time.sleep(0.5)  # wait a bit before trying again

@app.route('/')
def hello():
   """
   This function handles requests to the root URL ('/').
   It retrieves the current hit count and returns it in the response.
   """
   count = get_hit_count()
   return f"Hello PXL! I have been seen {count} times.\n"
