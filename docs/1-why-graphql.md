# Understanding GraphQL: Why It Exists

Let's say you're building an e-commerce application. You've got products, orders, and users, and every page in your frontend needs a slightly different combination of data from these.

Your product listing page just needs the `name` and `price` of each product. But your product detail page needs `name`, `price`, `description`, and maybe even the reviews and related orders. How do you design your API to handle both without pain?

If you're using REST, you'd probably create separate endpoints like `/products`, `/orders`, and `/users`, each returning a fixed shape of data. So what happens when two different pages need two different shapes of the same resource?

- If you reuse the same `/products` endpoint for both pages, the listing page ends up receiving `description` too, even though it never asked for it. That's wasted bandwidth.
- If you don't reuse it, you end up creating multiple endpoints just to satisfy slightly different data needs. That's wasted engineering effort.

In a small app, this barely matters. But in a real-world system with deeply nested data, this problem gets expensive fast. This is exactly the problem GraphQL was built to solve.

## So what does GraphQL actually do differently?

Instead of exposing a bunch of fixed-shape endpoints, GraphQL gives you a **single endpoint** (typically `/graphql`) that can handle all your reads and writes. The client sends a request that describes *exactly* which fields it wants, and the server responds with exactly that — nothing more, nothing less.

Going back to our product page example: the listing page can ask for just `name` and `price`, while the detail page asks for `name`, `price`, and `description`, all from the same endpoint. No wasted data, no extra endpoints.

There's another neat side effect of this. In REST, if you need a product along with its associated orders and user info, you'd typically fire off multiple requests. With GraphQL, you can ask for the product, its orders, and the user, all in a **single request**.

GraphQL operations come in two flavors:

| Term | Description |
|------|-------------|
| `Query` | A read-only operation that fetches data. Think of it as GraphQL's version of a REST `GET` request. |
| `Mutation` | A write operation that creates, updates, or deletes data. Think of it as GraphQL's version of `POST`, `PUT`, or `DELETE`. |

Simple enough, right? Now let's see how this actually gets wired up in a real Spring Boot application.

## Summary

Okay, let's quickly recap what we covered:

1. REST's fixed-shape endpoints force a trade-off between over-fetching and creating too many endpoints.
2. GraphQL replaces that with a single flexible endpoint where clients choose exactly which fields they want.
3. GraphQL can also fetch related resources (like a product's orders and user) in one request instead of many.
4. `Query` handles reads, `Mutation` handles writes.

## Next Steps

Now that you know *why* GraphQL exists, the next question is: how do you actually set this up in a Spring Boot application, and how does a request find its way from Postman to your service layer?

That's what we'll explore next.

---

[Setting Up GraphQL in Spring Boot -> **Next**](./2-setting-up-graphql-spring-boot.md)
