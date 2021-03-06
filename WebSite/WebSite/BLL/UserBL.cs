﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.Access_Data;
using NFCShoppingWebSite.DAL;
using System.Threading;

namespace NFCShoppingWebSite.BLL
{
    public class UserBL:IDisposable
    {

        IUserRepository mRepository;

        public UserBL()
        {
            mRepository = new UserRepository();
        }

        public UserBL(IUserRepository repository)
        {
            mRepository = repository;
        }

        #region 需求必要的业务逻辑处理实现定义

        /*通过获取用户信息调用*/
        public User GetUserByID(Int32 id)
        {
            try
            {
                List<User> users = mRepository.GetUsers().ToList();
                return users.Single(u => u.userID == id);
            }
            catch (Exception ex)
            {
                string exMessage = ex.Message;
                return null;
            }
        }

        /*注册的时候调用，判断用户名是否已经被注册的业务逻辑*/
        public bool IsExisted(String userName)
        {
            IEnumerable<User> users=mRepository.GetUsers().Where(u=>u.userName.Equals(userName));
            if (users.ToList().Count > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        /*获取签到次数排名前10位的用户*/
        public IEnumerable<User> GetTop10Users()
        {
            return mRepository.GetUsers().OrderByDescending(u=>u.visitedTimes).Take(10);
        }


        /*通过用户名查找用户的业务逻辑*/
        public User FindUserByUsername(String userName)
        {
            return mRepository.GetUsers().ToList().Find(u => u.userName.Equals(userName));
        }


        /*用户注册业务逻辑*/
        public User Regist(User user)
        {
            user.lastVisitedDate = DateTime.Now;
            user.visitedTimes = 0;
            try
            {
                mRepository.InsertUser(user, true);
                return FindUserByUsername(user.userName);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /*用户登录的业务逻辑*/
        public User Login(User user)
        {
            User temp = FindUserByUsername(user.userName);
            if (!temp.Equals(null))
            {
                if (temp.userPassword.Equals(user.userPassword))
                {
                    return temp;
                }
            }
            return null;
        }

        /*查询所有的用户*/
        public IEnumerable<User> GetUsers()
        {
            return mRepository.GetUsers();
        }

        /*用户签到函数*/
        public bool AutoAddVisitedTime(User origuser)
        {
            if (origuser.lastVisitedDate.Date.Equals(DateTime.Now.Date))
            {
                try
                {
                    User user = new User();
                    user.userID = origuser.userID;
                    user.userPassword = origuser.userPassword;
                    user.userName = origuser.userName;
                    user.visitedTimes = origuser.visitedTimes + 1;
                    user.gender = origuser.gender;
                    user.lastVisitedDate = origuser.lastVisitedDate;
                    mRepository.UpdateUser(user, origuser, true);
                    return true;
                }
                catch (Exception ex)
                {
                    return false;
                }
            }
            return false ;
        }

        /*删除用户*/
        public void DeleteUser(User user)
        {
            try
            {
                mRepository.DeleteUser(user, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }
        #endregion

        private bool mIsDisposed = false; 

        protected virtual void Dispose(bool disposing)
        {
            if (!this.mIsDisposed)
            {
                if (disposing)
                {
                    mRepository.Dispose();
                }
            }
            this.mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
    }
}