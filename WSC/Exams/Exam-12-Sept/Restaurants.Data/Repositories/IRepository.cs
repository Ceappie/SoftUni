﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Restaurants.Data.Repositories
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;

    public interface IRepository<T>
    {
        IQueryable<T> All();

        IQueryable<T> Find(Expression<Func<T, bool>> expression);

        T GetById(object id);

        T Add(T entity);

        T Update(T entity);

        void Delete(T entity);
    }
}
