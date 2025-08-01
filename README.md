## 🌟 **HyperBean – AI Assistant for hyperSonic Courier Service**

**HyperBean** is a Java-based AI customer service assistant built for a hypothetical courier company, **hyperSonic**. It’s designed to autonomously handle ~85% of common customer queries without requiring human intervention.

---

## 🤖 What Can HyperBean Do?

HyperBean is capable of managing **12+ specific customer service tasks**, including:  
📦 Canceling an order  
🏠 Changing the delivery address  
💳 Resolving overcharge issues  
❓ Answering policy-related questions  
🧑‍💼 Connecting the user to a human agent (on request)  
...and more!

---

## 🧠 How It Works

### **1. 🔍 Retrieval-Augmented Generation (RAG)**  
- Uses **Pinecone Vector Database**  
- Extracts precise answers from the Terms of Service  
- Ideal for answering general policy or info-related questions  

### **2. 🧰 Tool Integration (25 Tools Total)**  
- Guided by a detailed system prompt  
- Each tool maps to a specific customer task  
- HyperBean intelligently decides **when** and **which tool** to use based on the user’s intent  

---

## 💬 Live Chat System

- Built using **Spring WebSockets**  
- Enables customers to talk to a **human agent** when needed  
- Added as a **fallback** — HyperBean handles most queries independently  

---

## ⚙️ Tech Stack

🟨 Java  
🌱 Spring Boot — <span style="color:#2aa198">(MVC, JPA, Spring AI, WebSockets)</span>  
🌐 HTML & CSS  
🛢️ Neon PostgreSQL  
🧠 Pinecone — Vector DB  
