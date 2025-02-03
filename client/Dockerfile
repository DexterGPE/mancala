# Use an official Node.js image
FROM node:18

# Set working directory
WORKDIR /mancala/client

# Copy frontend files only
COPY ./client /mancala/client
COPY ./config /mancala/config

# Install dependencies
RUN npm install

# Expose frontend port
EXPOSE 5173

# Start frontend
CMD ["npm", "run", "dev"]
